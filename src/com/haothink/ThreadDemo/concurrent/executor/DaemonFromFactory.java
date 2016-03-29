package com.haothink.ThreadDemo.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
/**
 * 每个静态的ExecutorService创建方法都被重载为接受一个
 * ThreadFactory对象，而这个对象将被用来创建新线程
 * */
public class DaemonFromFactory {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec 
		= Executors.newCachedThreadPool(new DaemonThraedFactory());
		
		for(int i=0;i<10;i++)
			exec.execute(new TaskFactory());
		System.out.println("all ");
		TimeUnit.MILLISECONDS.sleep(500);
		
	}
}

class TaskFactory implements Runnable{

	@Override
	public void run() {
		try{
			while(true){
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread()+" "+this);
			}
		}catch(InterruptedException e){
			System.out.println("InterruptedException");
		}
		
	}
	
}

class DaemonThraedFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}
	
}
