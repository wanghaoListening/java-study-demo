package com.haothink.ThreadDemo.concurrent.procons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Lock 实现提供了比使用 synchronized 方法和语句可获得的更广泛的锁定操作。此实现允许更灵活的结构，
 * 可以具有差别很大的属性，可以支持多个相关的 Condition 对象。 
 * 
 * Condition 将 Object 监视器方法（wait、notify 和 notifyAll）分解成截然不同的对象，
 * 以便通过将这些对象与任意 Lock 实现组合使用，为每个对象提供多个等待 set（wait-set）。其中，Lock
 * 替代了 synchronized 方法和语句的使用，Condition 替代了 Object 监视器方法的使用。
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
		 * 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
		 * */
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(pro);
		exec.execute(con);
		exec.execute(pro);
		exec.execute(con);
		//启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
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
			this.name = name + count;//烤鸭1  烤鸭2  烤鸭3
			count++;//2 3 4
			System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);//生产烤鸭1 生产烤鸭2 生产烤鸭3
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
			System.out.println(Thread.currentThread().getName()+"...消费者........"+this.name);//消费烤鸭1
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
			r.set("烤鸭");
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

