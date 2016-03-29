package com.haothink.ThreadDemo.concurrent.blockingqueue;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 *同步队列SynchronousQueue实现原理
 *Java 6的并发编程包中的SynchronousQueue是一个没有数据缓冲的BlockingQueue，
 *生产者线程对其的插入操作put必须等待消费者的移除操作take，反过来也一样。
 *不像ArrayBlockingQueue或LinkedListBlockingQueue，
 *SynchronousQueue内部并没有数据缓存空间，你不能调用peek()方法来看队列中是否有数据元素，
 *因为数据元素只有当你试着取走的时候才可能存在，不取走而只想偷窥一下是不行的，
 *当然遍历这个队列的操作也是不允许的。队列头元素是第一个排队要插入数据的线程，
 *而不是要交换的数据。数据是在配对的生产者和消费者线程之间直接传递的，
 *并不会将数据缓冲数据到队列中。可以这样来理解：生产者和消费者互相等待对方，握手，然后一起离开。
 * */
public class SynchronousQueueDemo {
	public static void main(String[] args) {  
        SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();  
        new Customer(queue).start();  
        new Product(queue).start();  
    }  
    static class Product extends Thread{  
        SynchronousQueue<Integer> queue;  
        public Product(SynchronousQueue<Integer> queue){  
            this.queue = queue;  
        }  
        @Override  
        public void run(){  
            while(true){  
                int rand = new Random().nextInt(1000);  
                System.out.println("生产了一个产品："+rand);  
                System.out.println("等待三秒后运送出去...");  
                try {  
                    TimeUnit.SECONDS.sleep(3);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
                queue.offer(rand);  
            }  
        }  
    }  
    static class Customer extends Thread{  
        SynchronousQueue<Integer> queue;  
        public Customer(SynchronousQueue<Integer> queue){  
            this.queue = queue;  
        }  
        @Override  
        public void run(){  
            while(true){  
                try {  
                    System.out.println("消费了一个产品:"+queue.take());  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
                System.out.println("------------------------------------------");  
            }  
        }  
    }  
    /** 
     * 运行结果： 
     *  生产了一个产品：464 
        等待三秒后运送出去... 
        消费了一个产品:773 
        ------------------------------------------ 
        生产了一个产品：547 
        等待三秒后运送出去... 
        消费了一个产品:464 
        ------------------------------------------ 
        生产了一个产品：87 
        等待三秒后运送出去... 
        消费了一个产品:547 
        ------------------------------------------ 
     */  
}  