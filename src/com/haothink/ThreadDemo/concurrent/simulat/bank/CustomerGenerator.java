package com.haothink.ThreadDemo.concurrent.simulat.bank;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CustomerGenerator implements Runnable{
	private CustomerLine customers;
	private static Random random = new Random(47);
	public CustomerGenerator(CustomerLine cq){
		this.customers = cq;
	}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
				customers.put(new Customer(random.nextInt(1050)));
			}
		}catch(InterruptedException e){
			System.out.println("CustomerGenerator interrupted");
		}
		System.out.println("CustomerGenerator terminating");
	}
	
}
