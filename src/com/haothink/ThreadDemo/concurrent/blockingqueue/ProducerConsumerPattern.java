package com.haothink.ThreadDemo.concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 一个基于已链接节点的、范围任意的 blocking queue。此队列按 FIFO（先进先出）排序元素。
 * 队列的头部 是在队列中时间最长的元素。队列的尾部 是在队列中时间最短的元素。新元素插入到队列的尾部，
 * 并且队列获取操作会获得位于队列头部的元素。链接队列的吞吐量通常要高于基于数组的队列，
 * 但是在大多数并发应用程序中，其可预知的性能要低。 
        可选的容量范围构造方法参数作为防止队列过度扩展的一种方法。如果未指定容量，
        则它等于 Integer.MAX_VALUE。除非插入节点会使队列超出容量，
        否则每次插入后会动态地创建链接节点。 
 * */
public class ProducerConsumerPattern {
	 
    @SuppressWarnings("rawtypes")
	public static void main(String args[]){
 
     //Creating shared object
     BlockingQueue sharedQueue = new LinkedBlockingQueue();
 
     //Creating ProducerPattern and ConsumerPattern Thread
     Thread prodThread = new Thread(new ProducerPattern(sharedQueue));
     Thread consThread = new Thread(new ConsumerPattern(sharedQueue));
 
     //Starting producer and ConsumerPattern thread
     prodThread.start();
     consThread.start();
    }
 
}
 
//ProducerPattern Class in java
@SuppressWarnings("rawtypes")
class ProducerPattern implements Runnable {
 
   
	
	private final BlockingQueue sharedQueue;
 
    public ProducerPattern(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
 
 
	@SuppressWarnings("unchecked")
	@Override
    public void run() {
        for(int i=0; i<10; i++){
            try {
                System.out.println("Produced: " + i);
                sharedQueue.put(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProducerPattern.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 
}
 
//ConsumerPattern Class in Java
@SuppressWarnings("rawtypes")
class ConsumerPattern implements Runnable{
 
    
	private final BlockingQueue sharedQueue;
 
    public ConsumerPattern (BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
 
    @Override
    public void run() {
        while(true){
            try {
                System.out.println("Consumed: "+ sharedQueue.take());
            } catch (InterruptedException ex) {
                Logger.getLogger(ConsumerPattern.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 
}
 