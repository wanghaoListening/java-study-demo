package com.haothink.thread.concurrent.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
/**
 * Thread.UncaughtExceptionHandler��Java se5�е��½ӿڣ�����������
 * ÿ��ÿ��Thread�����϶�����һ���쳣��������
 * Thread.UncaughtExceptionHandler.uncaughtException�����߳�
 * ��δ������쳣����������ʱ����
 * ���������ֻ���ڲ������߳�ר�е�δ�����쳣������������²Żᱻ���á�
 * ϵͳ�����߳�ר�а汾�����û�з��֣������߳����Ƿ�����ר�е�uncaughtException
 * ���������û���֣��ٵ���defaultUncaughtExceptionHandler
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
				System.out.println(t.getName()+"������"+e);
				
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
