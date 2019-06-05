package com.haothink.thread.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CountdownLatchTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);

		final CountDownLatch cdAnswer = new CountDownLatch(3);

		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
				public void run(){
					try {
						System.out.println("Thread" + Thread.currentThread().getName() + 
								"Wait for the command");  

						cdOrder.await();
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

					}               
				}
			};            
			service.execute(runnable);
		}        
		try {
			Thread.sleep((long)(Math.random()*10000));

			System.out.println("Thread" + Thread.currentThread().getName() + 
					"The upcoming orders");                        
			cdOrder.countDown();
			System.out.println("Thread" + Thread.currentThread().getName() + 
					"Sent the command, are waiting for the result");    
			cdAnswer.await();
			System.out.println("Thread" + Thread.currentThread().getName() + 
					"Have received all the response. The results of this task has been completed");    
		} catch (Exception e) {
			e.printStackTrace();
		}          
		service.shutdown();

	}
}