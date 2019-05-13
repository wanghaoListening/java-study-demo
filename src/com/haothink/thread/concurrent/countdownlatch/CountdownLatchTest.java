package com.haothink.thread.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
7  * 
8  * @author wnaghao
9  *�ó�������ģ�ⷢ��������ִ�������Thread����ָ�ӹ٣��½�3��Thread����սʿ��
 *սʿһֱ�ȴ���ָ�ӹ��´������ָ�ӹ�û���´����
10 *��սʿ�Ƕ�����ȴ���һ�������´սʿ�Ƕ�ȥִ���Լ�������ָ�ӹٴ��ڵȴ�״̬��
 *սʿ������ִ������򱨸��
11 *ָ�ӹ٣�ָ�ӹ�������ȴ���
12 */
public class CountdownLatchTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool(); //����һ��Thread��
		final CountDownLatch cdOrder = new CountDownLatch(1);
		//ָ�ӹٵ��������Ϊ1��ָ�ӹ�һ�´������cutDown,��Ϊ0��սʿ��ִ������
		final CountDownLatch cdAnswer = new CountDownLatch(3);
		//��Ϊ������սʿ�����Գ�ʼֵΪ3��ÿһ��սʿִ�����������cutDownһ�Σ���������ִ����ϣ���Ϊ0����ָ�ӹ�ֹͣ�ȴ���        
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
				public void run(){
					try {
						System.out.println("Thread" + Thread.currentThread().getName() + 
								"Wait for the command");  
						//ʹ��ǰThread������������������֮ǰһֱ�ȴ�������Thread���жϡ�
						cdOrder.await(); //սʿ�Ƕ����ڵȴ�����״̬
						System.out.println("Thread" + Thread.currentThread().getName() + 
								"received command");                                
						Thread.sleep((long)(Math.random()*10000));                             
						System.out.println("Thread" + Thread.currentThread().getName() + 
								"Feedback the results");                                               
					                  
					} catch (Exception e) {
						e.printStackTrace();
					} finally{
						System.out.println("To complete the order");
						cdAnswer.countDown();
						 //����ִ����ϣ����ظ�ָ�ӹ٣�cdAnswer��1��  
					}               
				}
			};            
			service.execute(runnable);//ΪThread���������
		}        
		try {
			Thread.sleep((long)(Math.random()*10000));

			System.out.println("Thread" + Thread.currentThread().getName() + 
					"The upcoming orders");                        
			cdOrder.countDown(); //�������cdOrder��1�����ڵȴ���սʿ��ֹͣ�ȴ�תȥִ������
			System.out.println("Thread" + Thread.currentThread().getName() + 
					"Sent the command, are waiting for the result");    
			cdAnswer.await(); //����ͺ�ָ�ӹٴ��ڵȴ�״̬��һ��cdAnswerΪ0ʱֹͣ�ȴ���������ִ��
			System.out.println("Thread" + Thread.currentThread().getName() + 
					"Have received all the response. The results of this task has been completed");    
		} catch (Exception e) {
			e.printStackTrace();
		}          
		service.shutdown(); //���������ֹͣThread�ص�����Thread

	}
}