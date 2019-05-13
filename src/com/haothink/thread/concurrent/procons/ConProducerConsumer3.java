package com.haothink.thread.concurrent.procons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ����Lock������һ��condition�������������������������ͨ��
 * �������condition���󲻰����йش���״̬����Ϣ���������Ҫ�����ʾ
 * ����״̬����Ϣflag
 * ÿ��lock�ĵ��ö����������һ��try-finally�Ӿ䣬������֤���������
 * �¶������ͷ�������ʹ���ڽ��汾ʱ��������Ե���await()��signal()signalALL ()
 * */
public class ConProducerConsumer3 {
	public static void main(String[] args) {
		EEFood food = new EEFood();
		EEConsumer consumer = new EEConsumer(food);
		EEProducer producer = new EEProducer(food);
		
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(producer);
		exec.execute(consumer);
		exec.execute(producer);
		exec.execute(consumer);
		exec.shutdown();
	}
}

class EEFood{
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();//������condition
	private Condition condition2 = lock.newCondition();//������condition
	private String name;
	private int count = 1;
	private boolean flag = false;
	
	public void set(String name){
		try{
			lock.lock();
			while(flag)
				condition.await();
			this.name = name + count;//��Ѽ1  ��Ѽ2  ��Ѽ3
			count++;//2 3 4
			System.out.println(Thread.currentThread().getName()+"...������..."+this.name);//������Ѽ1 ������Ѽ2 ������Ѽ3
			flag = true;
			condition2.signal();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void out()//  t3
	{
		try {
			lock.lock();
			while(!flag)
				condition2.await();
			System.out.println(Thread.currentThread().getName()+"...������........"+this.name);//���ѿ�Ѽ1
			flag = false;
			condition.signal();
		} catch (Exception e) {

			e.printStackTrace();
		} finally{
			lock.unlock();
		}

	}
}

class EEConsumer implements Runnable{
	private EEFood r;
	EEConsumer(EEFood r)
	{
		this.r = r;
	}
	@Override
	public void run() {
		while(true)
		{
			r.out();
		}
		
	}
	
}

class EEProducer implements Runnable{
	private EEFood r;
	EEProducer(EEFood r)
	{
		this.r = r;
	}
	@Override
	public void run() {
		
		while(true)
		{
			r.set("��Ѽ");
		}
	}
	
}
