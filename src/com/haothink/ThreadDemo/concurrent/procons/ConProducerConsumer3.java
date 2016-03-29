package com.haothink.ThreadDemo.concurrent.procons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 单个Lock将产生一个condition对象，这个对象被用来管理任务间的通信
 * 但是这个condition对象不包含有关处理状态的信息，因此你需要额外表示
 * 处理状态的信息flag
 * 每个lock的调用都必须紧跟着一个try-finally子句，用来保证在所有情况
 * 下都可以释放锁。在使用内建版本时，任务可以调用await()，signal()signalALL ()
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
	private Condition condition = lock.newCondition();//生产者condition
	private Condition condition2 = lock.newCondition();//消费者condition
	private String name;
	private int count = 1;
	private boolean flag = false;
	
	public void set(String name){
		try{
			lock.lock();
			while(flag)
				condition.await();
			this.name = name + count;//烤鸭1  烤鸭2  烤鸭3
			count++;//2 3 4
			System.out.println(Thread.currentThread().getName()+"...生产者..."+this.name);//生产烤鸭1 生产烤鸭2 生产烤鸭3
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
			r.set("烤鸭");
		}
	}
	
}
