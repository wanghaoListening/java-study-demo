package com.haothink.ThreadDemo.concurrent.executor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock允许你尝试获取但最终未获取锁，这样如果其他人
 * 已经获取了锁，你就可以离开去做一些其他的事情。
 * 显示的lock对象在加锁和释锁方面对于内建的sychronized
 * 来说还赋予你更细粒度的控制力。这对于实现专有同步结构是很有
 * 用的。
 * */

public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();
	
	public void untimed(){
		boolean captured = lock.tryLock();
		try{
			System.out.println("tryLock(): "+captured);
		}finally{
			if(captured)
				lock.unlock();
		}
	}
	
	public void timed(){
		boolean captured = false;
		try{
			//尝试着去获取锁，该尝试在2秒之后失败
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		try{
			System.out.println("tryLock(2, TimeUnit.SECONDS)"+captured);
		}finally{
			if(captured)
				lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final AttemptLocking al = new AttemptLocking();
		al.untimed();
		al.timed();
		
		new Thread(){
			{setDaemon(true);}
			public void run(){
				al.lock.lock();
				System.out.println("acquired");
			}
		}.start();
		
		al.untimed();
		al.timed();
	}
}
