package com.haothink.thread.concurrent.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @author wanghao
 * java SE5��������������һ�����ԣ�����ReentrantLock������������߱�����
 * ���жϵ�������������synchronized�������ٽ�����������������ȫ��ͬ��
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
			//�����ǰ�߳�δ���жϣ����ȡ��
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
