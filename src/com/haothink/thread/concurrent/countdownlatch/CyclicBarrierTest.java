package com.haothink.thread.concurrent.countdownlatch;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * һ��ͬ�������࣬������һ���̻߳���ȴ���ֱ������ĳ���������ϵ� (common barrier point)��
 * ���漰һ��̶���С���̵߳ĳ����У���Щ�̱߳��벻ʱ�ػ���ȴ�����ʱ CyclicBarrier 
 * �����á���Ϊ�� barrier ���ͷŵȴ��̺߳�������ã����Գ���Ϊѭ�� �� barrier��
 * */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final  CyclicBarrier cb = new CyclicBarrier(3);//����CyclicBarrier��������3���������ϵ�
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
				public void run(){
					try {
						Thread.sleep((long)(Math.random()*10000));    
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"�������Ｏ�ϵص�1����ǰ����" + (cb.getNumberWaiting()+1) + "���Ѿ�������ڵȺ�");                       
						cb.await();//�������û�дﵽ�������ϵ㣬����̴߳��ڵȴ�״̬������ﵽ�������ϵ������д��ڵȴ����̶߳�������������
						 

						Thread.sleep((long)(Math.random()*10000));    
						System.out.println("�߳�" + Thread.currentThread().getName() + 								"�������Ｏ�ϵص�2����ǰ����" + (cb.getNumberWaiting()+1)+ "���Ѿ�������ڵȺ�");                        
						cb.await();    
						Thread.sleep((long)(Math.random()*10000));    
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"�������Ｏ�ϵص�3����ǰ����" + (cb.getNumberWaiting()+1) + "���Ѿ�������ڵȺ�");                        
						cb.await();                        
					} catch (Exception e) {
						e.printStackTrace();
					}                
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}