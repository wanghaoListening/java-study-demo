package com.haothink.thread;


/**
 * join内部使用了wait(delay);来进行实现的
 * while (isAlive()) {
 *    wait(0);
 * }
 * 线程的合并的含义就是 将几个并行线程的线程合并为一个单线程执行，应用场景是 当一个线程必须等
 * 待另一个线程执行完毕才能执行时，Thread类提供了join方法来完成这个功能，注意，它不是静态方法。
 */
class Demo implements Runnable
{
	@Override
	public void run()
	{
		for(int x=0; x<5000; x++)
		{
			System.out.println(Thread.currentThread().toString()+"....."+x);
			//Thread.yield();
		}
	}
}

public class  JoinDemo
{
	public static void main(String[] args) throws Exception
	{
		Demo d = new Demo();

		Thread t1 = new Thread(d);
		Thread t2 = new Thread(d);

		t1.start();

		t2.start();
		t1.join();

		for(int x=0; x<500; x++)
		{
			System.out.println(Thread.currentThread()+"....."+x);
		}
	}
}
