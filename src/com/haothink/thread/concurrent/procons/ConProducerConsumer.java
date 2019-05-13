package com.haothink.thread.concurrent.procons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Lock ʵ���ṩ�˱�ʹ�� synchronized ���������ɻ�õĸ��㷺��������������ʵ����������Ľṹ��
 * ���Ծ��в��ܴ�����ԣ�����֧�ֶ����ص� Condition ���� 
 * 
 * Condition �� Object ������������wait��notify �� notifyAll���ֽ�ɽ�Ȼ��ͬ�Ķ���
 * �Ա�ͨ������Щ���������� Lock ʵ�����ʹ�ã�Ϊÿ�������ṩ����ȴ� set��wait-set�������У�Lock
 * ����� synchronized ����������ʹ�ã�Condition ����� Object ������������ʹ�á�
 * 
 * */


public class ConProducerConsumer {
	
	public static void main(String[] args) {
		Food r = new Food();
		Producer pro = new Producer(r);
		Consumer con = new Consumer(r);

		/*Thread t0 = new Thread(pro);
		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(con);
		Thread t3 = new Thread(con);
		t0.start();
		t1.start();
		t2.start();
		t3.start();*/
		/**
		 * newCachedThreadPool
		 * ����һ���ɸ�����Ҫ�������̵߳��̳߳أ���������ǰ������߳̿���ʱ���������ǡ�
		 * */
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(pro);
		exec.execute(con);
		exec.execute(pro);
		exec.execute(con);
		//����һ��˳��رգ�ִ����ǰ�ύ�����񣬵�������������
		exec.shutdown();
	}
}


class Food
{	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private String name;
	private int count = 1;
	private boolean flag = false;
	
	public void set(String name)//  
	{	
		try {
			lock.lock();
			while(flag)
			condition.await();
			this.name = name + count;//��Ѽ1  ��Ѽ2  ��Ѽ3
			count++;//2 3 4
			System.out.println(Thread.currentThread().getName()+"...������..."+this.name);//������Ѽ1 ������Ѽ2 ������Ѽ3
			flag = true;
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
		
	}

	public void out()//  t3
	{
		try {
			lock.lock();
			while(!flag)
			condition.await();
			System.out.println(Thread.currentThread().getName()+"...������........"+this.name);//���ѿ�Ѽ1
			flag = false;
			condition.signalAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	
	}
}

class Producer implements Runnable
{
	private Food r;
	Producer(Food r)
	{
		this.r = r;
	}
	public void run()
	{
		while(true)
		{
			r.set("��Ѽ");
		}
	}
}

class Consumer implements Runnable
{
	private Food r;
	Consumer(Food r)
	{
		this.r = r;
	}
	public void run()
	{
		while(true)
		{
			r.out();
		}
	}
}

