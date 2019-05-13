package com.haothink.thread.concurrent.executor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock�����㳢�Ի�ȡ������δ��ȡ�����������������
 * �Ѿ���ȡ��������Ϳ����뿪ȥ��һЩ���������顣
 * ��ʾ��lock�����ڼ�����������������ڽ���sychronized
 * ��˵���������ϸ���ȵĿ������������ʵ��ר��ͬ���ṹ�Ǻ���
 * �õġ�
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
			//������ȥ��ȡ�����ó�����2��֮��ʧ��
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
