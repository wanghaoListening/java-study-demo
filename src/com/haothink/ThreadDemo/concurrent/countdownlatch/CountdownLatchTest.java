package com.haothink.ThreadDemo.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
7  * 
8  * @author wnaghao
9  *该程序用来模拟发送命令与执行命令，主Thread代表指挥官，新建3个Thread代表战士，
 *战士一直等待着指挥官下达命令，若指挥官没有下达命令，
10 *则战士们都必须等待。一旦命令下达，战士们都去执行自己的任务，指挥官处于等待状态，
 *战士们任务执行完毕则报告给
11 *指挥官，指挥官则结束等待。
12 */
public class CountdownLatchTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool(); //创建一个Thread池
		final CountDownLatch cdOrder = new CountDownLatch(1);
		//指挥官的命令，设置为1，指挥官一下达命令，则cutDown,变为0，战士们执行任务
		final CountDownLatch cdAnswer = new CountDownLatch(3);
		//因为有三个战士，所以初始值为3，每一个战士执行任务完毕则cutDown一次，当三个都执行完毕，变为0，则指挥官停止等待。        
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
				public void run(){
					try {
						System.out.println("Thread" + Thread.currentThread().getName() + 
								"Wait for the command");  
						//使当前Thread在锁存器倒计数至零之前一直等待，除非Thread被中断。
						cdOrder.await(); //战士们都处于等待命令状态
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
						 //任务执行完毕，返回给指挥官，cdAnswer减1。  
					}               
				}
			};            
			service.execute(runnable);//为Thread池添加任务
		}        
		try {
			Thread.sleep((long)(Math.random()*10000));

			System.out.println("Thread" + Thread.currentThread().getName() + 
					"The upcoming orders");                        
			cdOrder.countDown(); //发送命令，cdOrder减1，处于等待的战士们停止等待转去执行任务。
			System.out.println("Thread" + Thread.currentThread().getName() + 
					"Sent the command, are waiting for the result");    
			cdAnswer.await(); //命令发送后指挥官处于等待状态，一旦cdAnswer为0时停止等待继续往下执行
			System.out.println("Thread" + Thread.currentThread().getName() + 
					"Have received all the response. The results of this task has been completed");    
		} catch (Exception e) {
			e.printStackTrace();
		}          
		service.shutdown(); //任务结束，停止Thread池的所有Thread

	}
}