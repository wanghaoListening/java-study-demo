package com.haothink.ThreadDemo.concurrent.countdownlatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * CyclicBarrier ��ʾ��ұ˴˵ȴ�����Ҽ��Ϻú�ſ�ʼ��������ɢ�������iָ���ص㼯�����棬
 * ��ͺñ�������˾����Ա������ĩʱ�伯�彼��һ����
 * �ȸ��ԴӼҳ�������˾���Ϻ���ͬʱ��������԰���棬��ָ���ص㼯�Ϻ���ͬʱ��ʼ�Ͳ͡���
 * */
public class CyclicBarrierTest3 {
	public static void main(String [] args){
		ExecutorService service=Executors.newCachedThreadPool();
		final CyclicBarrier cb=new CyclicBarrier(3);  //�����߳�ͬʱ����
		for(int i=0;i<3;i++){         
			Runnable runnable=new Runnable(){
				public void run(){
					try {
						Thread.sleep((long)(Math.random()*10000));
						//getNumberWaiting()���ص�ǰ�����ϴ��ȴ��Ĳ�������Ŀ��
						//��ǰ������ await() �еĲ�������Ŀ��
						System.out.println("�߳�"+Thread.currentThread().getName()+
								"�������Ｏ�ϵص�1����ǰ����"+(cb.getNumberWaiting()+1)+"���ѵ���"+
								(cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));
						try {
							cb.await();
						} catch (BrokenBarrierException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Thread.sleep((long)(Math.random()*10000));
						
						System.out.println("�߳�"+Thread.currentThread().getName()+
								"�������Ｏ�ϵص�2����ǰ����"+(cb.getNumberWaiting()+1)+"���ѵ���"+
								(cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));
						try {
							cb.await();
						} catch (BrokenBarrierException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Thread.sleep((long)(Math.random()*10000));
						System.out.println("�߳�"+Thread.currentThread().getName()+
								"�������Ｏ�ϵص�3����ǰ����"+(cb.getNumberWaiting()+1)+"���ѵ���"+
								(cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));
						try {
							cb.await();
						} catch (BrokenBarrierException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}