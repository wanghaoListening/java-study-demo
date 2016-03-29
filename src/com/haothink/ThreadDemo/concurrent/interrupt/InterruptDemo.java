package com.haothink.ThreadDemo.concurrent.interrupt;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/**
 * 使用submit()来启动启动任务，就可以持有该任务的上下文。submit(r)将返回一个
 * 泛型的Future<?>，其中一个是未修饰的参数，因为你永远不会在其上调用get()持有
 * 这种Future的关键在于你可以在其上调用cancel()并因此可以使用它来中断某个任务
 * 如果将true传给cancel()那么它就会拥有该线程上调用interrupt()以停止这个的
 * 权限，因此Cancel()是一种中断Executor启动的单个线程的方式。
 * 
 * 结论：不能中断正在试图获取synchronized锁或者试图执行IO操作的线程。特别是
 * 创建执行IO的任务时，这意味着IO具有锁住你的多线程的潜在可能。特别是WEB的程序。
 * */
public class InterruptDemo {
	private static ExecutorService exec = Executors.newCachedThreadPool();
	static void test(Runnable r) throws InterruptedException{
		Future<?> f = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Interrupting" + r.getClass().getName());
		f.cancel(true);
		System.out.println("Interrupt sent to " + r.getClass().getName());
	}
	
	public static void main(String[] args) throws InterruptedException {
		test(new SleepBloked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0);
	}
}

class SleepBloked implements Runnable{

	@Override
	public void run() {
		
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
		
			System.out.println("InterruptedException");
		}
		System.out.println("Exiting SleepBlocked.run()");
		
	}
	
}

 class IOBlocked implements Runnable{
	private InputStream in;
	
	public IOBlocked(InputStream is){
		in = is;
	}
	@Override
	public void run() {
		try {
			System.out.println("Waiting for read()");
			in.read();
		} catch (Exception e) {
		
			if(Thread.currentThread().isInterrupted()){
				System.out.println("Interrupted from blocked I/O");
			}else{
				throw new RuntimeException();
			}
		}
		System.out.println("Exiting IOVlocked.run()");
	}
	 
 }
 
 class SynchronizedBlocked implements Runnable{
	 
	public synchronized void f(){
		while(true)
			Thread.yield();
	}
	
	public SynchronizedBlocked(){
		new Thread(){
			public void run(){
				f();
			}
		}.start();
	}

	@Override
	public void run() {
		System.out.println("Trying to call f()");
		f();
		System.out.println("Exiting ynchronizedBlocked.run()");
		
	}
	 
 }
