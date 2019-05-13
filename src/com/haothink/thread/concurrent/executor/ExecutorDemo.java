package com.haothink.thread.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		for(int i=0;i<5;i++)
			exec.execute(new Walk());
		exec.shutdown();
	}
}

class Walk implements Runnable{
private volatile int i=0;
	@Override
	public void run() {
		while(true){
			synchronized (this) {
				System.out.println("hello"+(i++));
			}
			
		}
		
	}
	
}
