package com.haothink.thread.concurrent.lockfree;

public class Counter {
	private volatile int max = 0;
	
	public synchronized void set(int value){
		if(value > max)
			max = value;
	}
	
	public int getMax(){
		return max;
	}
}
