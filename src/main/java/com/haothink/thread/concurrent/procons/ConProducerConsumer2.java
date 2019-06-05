package com.haothink.thread.concurrent.procons;
/*
jdk1.5�Ժ�ͬ��������װ���˶��� 
��������������ʽ��ʽ���嵽�˸ö����У�
����ʽ�����������ʾ������

Lock�ӿڣ� ���������ͬ����������ͬ����������ͬ������ʽ�����������ʵ��������
ͬʱ��Ϊ������һ�����ϼ��϶����������
lock():��ȡ����
unlock():�ͷ�����ͨ����Ҫ����finally������С�


Condition�ӿڣ����������Object�е�wait notify notifyAll������
			����Щ�������������������˷�װ�����Condition����������
			����������������ϡ�
await();
signal();
signalAll();



 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConProducerConsumer2 {

	public static void main(String[] args) {
		EFood r = new EFood();
		EProducer pro = new EProducer(r);
		EConsumer con = new EConsumer(r);

		Thread t0 = new Thread(pro);
		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(con);
		Thread t3 = new Thread(con);
		t0.start();
		t1.start();
		t2.start();
		t3.start();
	}
}
class EFood
{	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();//������condition
	private Condition condition2 = lock.newCondition();//������condition
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
			condition2.signal();
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

class EProducer implements Runnable
{
	private EFood r;
	EProducer(EFood r)
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

class EConsumer implements Runnable
{
	private EFood r;
	EConsumer(EFood r)
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
