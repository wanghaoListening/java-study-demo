package com.haothink.ThreadDemo.concurrent.simulat.bank;

public class Customer {
	private final int serviceTime;
	public Customer(int tm){
		this.serviceTime = tm;
	}
	
	public int getServiceTime(){
		return serviceTime;
	}
	
	public String toString(){
		return "[" + serviceTime + "]";
	}
}
