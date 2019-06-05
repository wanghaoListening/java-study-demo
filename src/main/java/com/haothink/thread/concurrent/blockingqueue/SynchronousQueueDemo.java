package com.haothink.thread.concurrent.blockingqueue;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 *ͬ������SynchronousQueueʵ��ԭ��
 *Java 6�Ĳ�����̰��е�SynchronousQueue��һ��û�����ݻ����BlockingQueue��
 *�������̶߳���Ĳ������put����ȴ������ߵ��Ƴ�����take��������Ҳһ����
 *����ArrayBlockingQueue��LinkedListBlockingQueue��
 *SynchronousQueue�ڲ���û�����ݻ���ռ䣬�㲻�ܵ���peek()���������������Ƿ�������Ԫ�أ�
 *��Ϊ����Ԫ��ֻ�е�������ȡ�ߵ�ʱ��ſ��ܴ��ڣ���ȡ�߶�ֻ��͵��һ���ǲ��еģ�
 *��Ȼ����������еĲ���Ҳ�ǲ�����ġ�����ͷԪ���ǵ�һ���Ŷ�Ҫ�������ݵ��̣߳�
 *������Ҫ���������ݡ�����������Ե������ߺ��������߳�֮��ֱ�Ӵ��ݵģ�
 *�����Ὣ���ݻ������ݵ������С�������������⣺�����ߺ������߻���ȴ��Է������֣�Ȼ��һ���뿪��
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
                System.out.println("������һ����Ʒ��"+rand);  
                System.out.println("�ȴ���������ͳ�ȥ...");  
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
                    System.out.println("������һ����Ʒ:"+queue.take());  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
                System.out.println("------------------------------------------");  
            }  
        }  
    }  
    /** 
     * ���н���� 
     *  ������һ����Ʒ��464 
        �ȴ���������ͳ�ȥ... 
        ������һ����Ʒ:773 
        ------------------------------------------ 
        ������һ����Ʒ��547 
        �ȴ���������ͳ�ȥ... 
        ������һ����Ʒ:464 
        ------------------------------------------ 
        ������һ����Ʒ��87 
        �ȴ���������ͳ�ȥ... 
        ������һ����Ʒ:547 
        ------------------------------------------ 
     */  
}  