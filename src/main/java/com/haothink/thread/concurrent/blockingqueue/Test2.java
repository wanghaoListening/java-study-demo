package com.haothink.thread.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
/**
 * ������֧�ֵ��н��������С��˶��а� FIFO���Ƚ��ȳ���ԭ���Ԫ�ؽ�������
 * ���е�ͷ�� ���ڶ����д���ʱ�����Ԫ�ء�
 * ���е�β�� ���ڶ����д���ʱ����̵�Ԫ�ء�
 * ��Ԫ�ز��뵽���е�β�������л�ȡ�������ǴӶ���ͷ����ʼ���Ԫ�ء�
 * �̶���С�����������б��������߲����Ԫ�غ�ʹ������ȡ��Ԫ�ء�
 * һ�������������Ļ��������Ͳ�������������������ͼ�����������з���Ԫ�ػᵼ�²�����������
 * ��ͼ�ӿն�������ȡԪ�ؽ���������������
 * ����֧�ֶԵȴ����������̺߳�ʹ�����߳̽�������Ŀ�ѡ��ƽ���ԡ�Ĭ������£�����֤����������
 * Ȼ����ͨ������ƽ�� (fairness) ����Ϊ true ������Ķ��������� FIFO ˳������̡߳�
 * ��ƽ��ͨ���ή������������Ҳ�����˿ɱ��Ժͱ����ˡ���ƽ���ԡ���
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
                    System.out.println("�Ӷ���ȡ��һ��Ԫ�أ�����ʣ��"+queue.size()+"��Ԫ��");
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
                    System.out.println("�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺"+(queueSize-queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}