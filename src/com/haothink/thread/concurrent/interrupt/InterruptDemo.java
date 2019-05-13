package com.haothink.thread.concurrent.interrupt;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/**
 * ʹ��submit()�������������񣬾Ϳ��Գ��и�����������ġ�submit(r)������һ��
 * ���͵�Future<?>������һ����δ���εĲ�������Ϊ����Զ���������ϵ���get()����
 * ����Future�Ĺؼ���������������ϵ���cancel()����˿���ʹ�������ж�ĳ������
 * �����true����cancel()��ô���ͻ�ӵ�и��߳��ϵ���interrupt()��ֹͣ�����
 * Ȩ�ޣ����Cancel()��һ���ж�Executor�����ĵ����̵߳ķ�ʽ��
 * 
 * ���ۣ������ж�������ͼ��ȡsynchronized��������ͼִ��IO�������̡߳��ر���
 * ����ִ��IO������ʱ������ζ��IO������ס��Ķ��̵߳�Ǳ�ڿ��ܡ��ر���WEB�ĳ���
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
