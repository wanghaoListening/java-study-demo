package com.haothink.ThreadDemo.concurrent.procons;
/*
jdk1.5以后将同步和锁封装成了对象。 
并将操作锁的隐式方式定义到了该对象中，
将隐式动作变成了显示动作。

Lock接口： 出现替代了同步代码块或者同步函数。将同步的隐式锁操作变成现实锁操作。
同时更为灵活。可以一个锁上加上多组监视器。
lock():获取锁。
unlock():释放锁，通常需要定义finally代码块中。


Condition接口：出现替代了Object中的wait notify notifyAll方法。
			将这些监视器方法单独进行了封装，变成Condition监视器对象。
			可以任意锁进行组合。
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
	private Condition condition = lock.newCondition();//生产者condition
	private Condition condition2 = lock.newCondition();//消费者condition
	private String name;
	private int count = 1;
	private boolean flag = false;

	public void set(String name)//  
	{	
		try {
			lock.lock();
			while(flag)
				condition.await();
			this.name = name + count;//烤鸭1  烤鸭2  烤鸭3
			count++;//2 3 4
			System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);//生产烤鸭1 生产烤鸭2 生产烤鸭3
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
			System.out.println(Thread.currentThread().getName()+"...消费者........"+this.name);//消费烤鸭1
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
			r.set("烤鸭");
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
