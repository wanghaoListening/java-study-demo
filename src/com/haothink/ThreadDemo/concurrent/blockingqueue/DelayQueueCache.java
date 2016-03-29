package com.haothink.ThreadDemo.concurrent.blockingqueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DelayQueue是一个使用优先队列（PriorityQueue）实现的BlockingQueue，优先队列的比较基准值是时间。
 * public class DelayQueue<E extends Delayed>
 * extends AbstractQueue<E>
 * implements BlockingQueue<E>Delayed 元素的一个无界阻塞队列，
 * 只有在延迟期满时才能从中提取元素。该队列的头部 是延迟期满后保存时间最长的 Delayed 元素。
 * 如果延迟都还没有期满，则队列没有头部，并且 poll 将返回 null。
 * 当一个元素的 getDelay(TimeUnit.NANOSECONDS) 方法返回一个小于等于 0 的值时，
 * 将发生到期。即使无法使用 take 或 poll 移除未到期的元素，也不会将这些元素作为正常元素对待。
 * 例如，size 方法同时返回到期和未到期元素的计数。此队列不允许使用 null 元素。 
 * a) 关闭空闲连接。服务器中，有很多客户端的连接，空闲一段时间之后需要关闭之。
 * b) 缓存。缓存中的对象，超过了空闲时间，需要从缓存中移出。
 * c) 任务超时处理。在网络协议滑动窗口请求应答式交互时，处理超时未响应的请求。
 * 大神温少speak 随后在实际工作中，应用在session超时管理，网络应答通讯协议的请求超时处理
 * Delayed扩展了Comparable接口，比较的基准为延时的时间值，
 * Delayed接口的实现类getDelay的返回值应为固定值（final）。
 * DelayQueue内部是使用PriorityQueue实现的。
 * DelayQueue = BlockingQueue + PriorityQueue + Delayed
 * DelayQueue是一个使用优先队列（PriorityQueue）实现的BlockingQueue，
 * 优先队列的比较基准值是时间。
 * 
 * */


public class DelayQueueCache<K, V> {
    private static final Logger LOG = Logger.getLogger(DelayQueueCache.class.getName());

    private ConcurrentMap<K, V> cacheObjMap = new ConcurrentHashMap<K, V>();

    private DelayQueue<DelayItem<Pair<K, V>>> q = new DelayQueue<DelayItem<Pair<K, V>>>();

    private Thread daemonThread;

    public DelayQueueCache() {

        Runnable daemonTask = new Runnable() {
            public void run() {
                daemonCheck();
            }
        };

        daemonThread = new Thread(daemonTask);
        daemonThread.setDaemon(true);
        daemonThread.setName("Cache Daemon");
        daemonThread.start();
    }

    private void daemonCheck() {

        if (LOG.isLoggable(Level.INFO))
            LOG.info("cache service started.");

        for (;;) {
            try {
                DelayItem<Pair<K, V>> delayItem = q.take();
                if (delayItem != null) {
                    // 超时对象处理
                    Pair<K, V> pair = delayItem.getItem();
                    cacheObjMap.remove(pair.first, pair.second); // compare and remove
                }
            } catch (InterruptedException e) {
                if (LOG.isLoggable(Level.SEVERE))
                    LOG.log(Level.SEVERE, e.getMessage(), e);
                break;
            }
        }

        if (LOG.isLoggable(Level.INFO))
            LOG.info("cache service stopped.");
    }

    // 添加缓存对象
    public void put(K key, V value, long time, TimeUnit unit) {
        V oldValue = cacheObjMap.put(key, value);
        if (oldValue != null)
            q.remove(key);

        long nanoTime = TimeUnit.NANOSECONDS.convert(time, unit);
        q.put(new DelayItem<Pair<K, V>>(new Pair<K, V>(key, value), nanoTime));
    }

    public V get(K key) {
        return cacheObjMap.get(key);
    }

    // 测试入口函数
    public static void main(String[] args) throws Exception {
        DelayQueueCache<Integer, String> cache = new DelayQueueCache<Integer, String>();
        cache.put(1, "aaaa", 3, TimeUnit.SECONDS);

        Thread.sleep(1000 * 2);
        {
            String str = cache.get(1);
            System.out.println(str);
        }

        Thread.sleep(1000 * 2);
        {
            String str = cache.get(1);
            System.out.println(str);
        }
    }
}

class Pair<K,V>{
	public K first;
	
	public V second;
	
	public Pair(){}
	
	public Pair(K first,V second){
		this.first = first;
		this.second = second;
	}
}

class DelayItem<T> implements Delayed{
	/**
	 * ns（nanosecond）：纳秒， 时间单位。一秒的10亿分之一，即等于10的负9次方秒。常用作 内存读写速度的单位。
　　	 * 1纳秒=0.000001 毫秒
　　       *   1纳秒=0.00000 0001秒 
	  *  这个返回值是一个从确定的值算起的，但是这个值是任意的，可能是一个未来的时间，所以返回值有可能是负数
	 * */
	 /** Base of nanosecond timings, to avoid wrapping */
    private static final long NANO_ORIGIN = System.nanoTime();

    /**
     * Returns nanosecond time offset by origin
     */
    final static long now() {
        return System.nanoTime() - NANO_ORIGIN;
    }

    /**
     * Sequence number to break scheduling ties, and in turn to guarantee FIFO order among tied
     * entries.
     */
    private static final AtomicLong sequencer = new AtomicLong(0);

    /** Sequence number to break ties FIFO */
    private final long sequenceNumber;

    /** The time the task is enabled to execute in nanoTime units */
    private final long time;

    private final T item;

	@SuppressWarnings("rawtypes")
	@Override
	public int compareTo(Delayed other) {
        if (other == this) // compare zero ONLY if same object
            return 0;
        if (other instanceof DelayItem) {
            DelayItem x = (DelayItem) other;
            long diff = time - x.time;
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;
            else if (sequenceNumber < x.sequenceNumber)
                return -1;
            else
                return 1;
        }
        long d = (getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS));
        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
    }


	public DelayItem(T submit, long timeout) {
        this.time = now() + timeout;
        this.item = submit;
        this.sequenceNumber = sequencer.getAndIncrement();
    }

    public T getItem() {
        return this.item;
    }

    public long getDelay(TimeUnit unit) {
        long d = unit.convert(time - now(), TimeUnit.NANOSECONDS);
        return d;
    }
	
}
