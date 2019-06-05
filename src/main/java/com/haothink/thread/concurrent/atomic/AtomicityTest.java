package com.haothink.thread.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable{
	
	private int i=0;
	public int getValue(){
		return i;
	}
	
	private synchronized void Increment(){
		i++;
		
	}
	@Override
	public void run() {
		
		while(true)
			Increment();
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest ait = new AtomicityTest();
		exec.execute(ait);
		while(true){
			int val  = ait.getValue();
			if(val % 2 !=0){
				System.out.println(val);
				System.exit(0);
			}
		}
	}
	
}
