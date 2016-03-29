package com.haothink.ThreadDemo.concurrent.executor;

import java.util.concurrent.TimeUnit;
/**
 * Daemon线程被设置成后台线程，然后派生出很多子线程，这些
 * 子线程没有被显示的设置为后台模式，不过他们确实是后台线程
 * */
public class Daemons {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Daemon());
		t.setDaemon(true);
		t.start();
		System.out.println("t.isDaemon() = "+t.isDaemon());
		TimeUnit.SECONDS.sleep(1);
	}
}

class DaemonSpawn implements Runnable{

	@Override
	public void run() {
		while(true)
			Thread.yield();
		
	}
	
}

class Daemon implements Runnable{
	private Thread[] t = new Thread[10];
	@Override
	public void run() {
		for(int i=0;i<t.length;i++){
			t[i]=new Thread(new DaemonSpawn());
			t[i].start();
			System.out.println("DaemonSpawn "+i+" started");
		}
		
		for(int i=0;i<t.length;i++)
			System.out.println("t["+i+"]isDaemon() = "+t[i].isDaemon());
		while(true)
			Thread.yield();
		
	}
	
}
