package com.haothink.thread.concurrent.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExceptionThread2 {
	
	public static void main(String[] args) {
		ExecutorService exec 
		= Executors.newCachedThreadPool(new HandlerThreadFactory());
	
		exec.execute(new ExceptionTask());
	}
	
}

class HandlerThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(t.getName()+e);
				
			}
		});
		return t;
	}
	
}

class ExceptionTask implements Runnable{

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("run() by"+t);
		throw new RuntimeException();
		
	}
	
}
