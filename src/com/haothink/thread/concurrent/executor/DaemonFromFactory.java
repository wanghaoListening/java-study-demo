package com.haothink.thread.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
/**
 * ÿ����̬��ExecutorService����������������Ϊ����һ��
 * ThreadFactory���󣬶�������󽫱������������߳�
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
