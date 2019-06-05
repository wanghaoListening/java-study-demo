package com.haothink.thread.concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * һ�����������ӽڵ�ġ���Χ����� blocking queue���˶��а� FIFO���Ƚ��ȳ�������Ԫ�ء�
 * ���е�ͷ�� ���ڶ�����ʱ�����Ԫ�ء����е�β�� ���ڶ�����ʱ����̵�Ԫ�ء���Ԫ�ز��뵽���е�β����
 * ���Ҷ��л�ȡ��������λ�ڶ���ͷ����Ԫ�ء����Ӷ��е�������ͨ��Ҫ���ڻ�������Ķ��У�
 * �����ڴ��������Ӧ�ó����У����Ԥ֪������Ҫ�͡� 
        ��ѡ��������Χ���췽��������Ϊ��ֹ���й�����չ��һ�ַ��������δָ��������
        �������� Integer.MAX_VALUE�����ǲ���ڵ��ʹ���г���������
        ����ÿ�β����ᶯ̬�ش������ӽڵ㡣 
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
 