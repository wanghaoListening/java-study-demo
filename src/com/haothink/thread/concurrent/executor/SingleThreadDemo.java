package com.haothink.thread.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 1.SingleThreadExecutor���߳���Ϊ1��FixedThreadExecutor
 * 2.��SingleThreadExecutor�ύ���������ô��Щ�����Ŷ�
 * �ȴ�ֱ��ǰһ���������������ά���Լ�������������У����أ�
 * 
 * 
 * */
public class SingleThreadDemo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		exec.execute(new Task());
		exec.execute(new Task());
		exec.execute(new Task());
	}
	
	
}


class Task implements Runnable{
	private int i = 1000000;
	@Override
	public void run() {
		while(true){
			synchronized (this) {
				if(i>=0)
				System.out.println(Thread.currentThread().getName()+".."+(i--));
			}
		}
		
	}
	
}