package com.haothink.ThreadDemo.concurrent.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @author wanghao
 * java SE5并发类库中添加了一个特性，即在ReentrantLock上阻塞的任务具备可以
 * 、中断的能力，这与在synchronized方法或临界区上阻塞的任务完全不同。
 * 
 * */
public class Interrupting {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		t.interrupt();
	}
}

class BlockedMutex{
	private Lock lock = new ReentrantLock();
	
	public BlockedMutex(){
		lock.lock();
	}
	
	public void f(){
		try{
			//如果当前线程未被中断，则获取锁
			lock.lockInterruptibly();
			System.out.println("BlockedMutex f()");
		}catch(Exception e){
			System.out.println("Interrupted from  out of blocked call");
		}
	}
}

class Blocked2 implements Runnable{
	BlockedMutex bm = new BlockedMutex();
	@Override
	public void run() {
		System.out.println("waiting for f() in BlockedMutex");
		bm.f();
		System.out.println("Broken out of blocked call");
	}
	
}
