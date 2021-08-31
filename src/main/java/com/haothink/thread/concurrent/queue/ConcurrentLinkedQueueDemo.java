package com.haothink.thread.concurrent.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by wanghao on 2021/8/31
 *
 * Java 提供的线程安全的 Queue 可以分为阻塞队列和非阻塞队列，其中阻塞队列的典型例子是 BlockingQueue，非阻塞队列的典型例子是
 * ConcurrentLinkedQueue，在实际应用中要根据实际需要选用阻塞队列或者非阻塞队列。 阻塞队列可以通过加锁来实现，非阻塞队列可以通过 CAS 操作实现。
 *
 * 从名字可以看出，ConcurrentLinkedQueue这个队列使用链表作为其数据结构．ConcurrentLinkedQueue 应该算是在高并发环境中性能最好的队列了。
 * 它之所有能有很好的性能，是因为其内部复杂的实现。
 *
 * ConcurrentLinkedQueue 内部代码我们就不分析了，大家知道 ConcurrentLinkedQueue 主要使用 CAS 非阻塞算法来实现线程安全就好了。
 *
 * ConcurrentLinkedQueue 适合在对性能要求相对较高，同时对队列的读写存在多个线程同时进行的场景，即如果对队列加锁的成本较高则适合使用无锁
 * 的 ConcurrentLinkedQueue 来替代。
 *
 * ConcurrentLinkedQueue 通过无锁的方式，即内部是遵循 CAS（比较并交换）的方式来实现，实现了高并发状态下的高性能，因此通常 ConcurrentLinkedQueue
 * 的性能好于 BlockingQueue（ArrayBlockingQueue 一把锁，LinkedBlockingQueue 存取两把锁）
 *
 * size() 方法返回队列元素个数，由于该方法需要遍历一遍集合的，效率很慢，所以尽量要避免用 size。此外，如果在执行期间添加或删除元素。对于此方法，返回的结果可能不准确。
 **/
public class ConcurrentLinkedQueueDemo {

  public static void main(String[] args) throws InterruptedException {

    // 创建一个ConcurrentLinkedQueue类型的非阻塞队列
    ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    // 创建两个生产者和一一个消费者
    ProducerThread producerThread1 = new ProducerThread("11111生产者1", queue);
    ProducerThread producerThread2 = new ProducerThread("22222生产者2", queue);
    ConsumerThread consumerThread1 = new ConsumerThread("---->消费者1", queue);
    Thread t1 = new Thread(producerThread1);
    Thread t2 = new Thread(producerThread2);
    Thread c1 = new Thread(consumerThread1);
    t1.start();
    t2.start();
    c1.start();

    // 执行3s后，生产者不再生产
    Thread.sleep(3 * 1000);
    producerThread1.stopThread();
    producerThread2.stopThread();
  }



}
