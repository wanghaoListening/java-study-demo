package com.haothink.ThreadDemo.concurrent.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/**
 * Thread.UncaughtExceptionHandler是Java se5中的新接口，它允许你在
 * 每个每个Thread对象上都附着一个异常处理器。
 * Thread.UncaughtExceptionHandler.uncaughtException会在线程
 * 因未捕获的异常而濒临死亡时调用
 * 这个处理器只有在不存在线程专有的未捕获异常处理器的情况下才会被调用。
 * 系统会检查线程专有版本，如果没有发现，则检查线程组是否有其专有的uncaughtException
 * 方法，如果没发现，再调用defaultUncaughtExceptionHandler
 * */
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
				System.out.println(t.getName()+"出现了"+e);
				
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
