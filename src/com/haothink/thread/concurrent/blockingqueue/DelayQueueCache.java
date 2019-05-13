package com.haothink.thread.concurrent.blockingqueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DelayQueue��һ��ʹ�����ȶ��У�PriorityQueue��ʵ�ֵ�BlockingQueue�����ȶ��еıȽϻ�׼ֵ��ʱ�䡣
 * public class DelayQueue<E extends Delayed>
 * extends AbstractQueue<E>
 * implements BlockingQueue<E>Delayed Ԫ�ص�һ���޽��������У�
 * ֻ�����ӳ�����ʱ���ܴ�����ȡԪ�ء��ö��е�ͷ�� ���ӳ������󱣴�ʱ����� Delayed Ԫ�ء�
 * ����ӳٶ���û�������������û��ͷ�������� poll ������ null��
 * ��һ��Ԫ�ص� getDelay(TimeUnit.NANOSECONDS) ��������һ��С�ڵ��� 0 ��ֵʱ��
 * ���������ڡ���ʹ�޷�ʹ�� take �� poll �Ƴ�δ���ڵ�Ԫ�أ�Ҳ���Ὣ��ЩԪ����Ϊ����Ԫ�ضԴ���
 * ���磬size ����ͬʱ���ص��ں�δ����Ԫ�صļ������˶��в�����ʹ�� null Ԫ�ء� 
 * a) �رտ������ӡ��������У��кܶ�ͻ��˵����ӣ�����һ��ʱ��֮����Ҫ�ر�֮��
 * b) ���档�����еĶ��󣬳����˿���ʱ�䣬��Ҫ�ӻ������Ƴ���
 * c) ����ʱ����������Э�黬����������Ӧ��ʽ����ʱ������ʱδ��Ӧ������
 * ��������speak �����ʵ�ʹ����У�Ӧ����session��ʱ��������Ӧ��ͨѶЭ�������ʱ����
 * Delayed��չ��Comparable�ӿڣ��ȽϵĻ�׼Ϊ��ʱ��ʱ��ֵ��
 * Delayed�ӿڵ�ʵ����getDelay�ķ���ֵӦΪ�̶�ֵ��final����
 * DelayQueue�ڲ���ʹ��PriorityQueueʵ�ֵġ�
 * DelayQueue = BlockingQueue + PriorityQueue + Delayed
 * DelayQueue��һ��ʹ�����ȶ��У�PriorityQueue��ʵ�ֵ�BlockingQueue��
 * ���ȶ��еıȽϻ�׼ֵ��ʱ�䡣
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
                    // ��ʱ������
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

    // ��ӻ������
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

    // ������ں���
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
	 * ns��nanosecond�������룬 ʱ�䵥λ��һ���10�ڷ�֮һ��������10�ĸ�9�η��롣������ �ڴ��д�ٶȵĵ�λ��
����	 * 1����=0.000001 ����
����       *   1����=0.00000 0001�� 
	  *  �������ֵ��һ����ȷ����ֵ����ģ��������ֵ������ģ�������һ��δ����ʱ�䣬���Է���ֵ�п����Ǹ���
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
