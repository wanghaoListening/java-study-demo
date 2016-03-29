package com.haothink.ThreadDemo.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
/**
 * 由数组支持的有界阻塞队列。此队列按 FIFO（先进先出）原则对元素进行排序。
 * 队列的头部 是在队列中存在时间最长的元素。
 * 队列的尾部 是在队列中存在时间最短的元素。
 * 新元素插入到队列的尾部，队列获取操作则是从队列头部开始获得元素。
 * 固定大小的数组在其中保持生产者插入的元素和使用者提取的元素。
 * 一旦创建了这样的缓存区，就不能再增加其容量。试图向已满队列中放入元素会导致操作受阻塞；
 * 试图从空队列中提取元素将导致类似阻塞。
 * 此类支持对等待的生产者线程和使用者线程进行排序的可选公平策略。默认情况下，不保证是这种排序。
 * 然而，通过将公平性 (fairness) 设置为 true 而构造的队列允许按照 FIFO 顺序访问线程。
 * 公平性通常会降低吞吐量，但也减少了可变性和避免了“不平衡性”。
 * */
public class Test2 {
    private int queueSize = 10;
    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize,true);
     
    public static void main(String[] args)  {
    	Test2 test = new Test2();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();
         
        producer.start();
        consumer.start();
    }
     
    class Consumer extends Thread{
         
        @Override
        public void run() {
            consume();
        }
         
        private void consume() {
            while(true){
                try {
                    queue.take();
                    System.out.println("从队列取走一个元素，队列剩余"+queue.size()+"个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
     
    class Producer extends Thread{
         
        @Override
        public void run() {
            produce();
        }
         
        private void produce() {
            while(true){
                try {
                    queue.put(1);
                    System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}