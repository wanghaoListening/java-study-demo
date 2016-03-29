package com.haothink.ThreadDemo;



/*
等待/唤醒机制。 

涉及的方法：

1，wait(): 让线程处于冻结状态，被wait的线程会被存储到线程池中。相应的琐也会被释放
2，notify():唤醒线程池中一个线程(任意).
3，notifyAll():唤醒线程池中的所有线程。
在其他线程调用此对象的 notify() 方法或 notifyAll() 方法前，导致当前线程等待。换句话说，
此方法的行为就好像它仅执行 wait(0) 调用一样。 
当前线程必须拥有此对象监视器(也就是我们所说的锁所以这些方法都必须定义在同步中。)。
 

这些方法都必须定义在同步中。
因为这些方法是用于操作线程状态的方法。
必须要明确到底操作的是哪个锁上的线程。


为什么操作线程的方法wait notify notifyAll定义在了Object类中？ 

因为这些方法是监视器的方法。监视器其实就是锁。
锁可以是任意的对象，任意的对象都可以调用的方法一定定义在Object类中。

*/
//资源
class IResource
{
	String name;
	String sex;
	boolean flag = false;
}


//输入
class InputR implements Runnable
{
	IResource r ;
//	Object obj = new Object();
	InputR(IResource r2)
	{
		this.r = r2;
	}
	public void run()
	{
		int x = 0;
		while(true)
		{
			synchronized(r)
			{
				if(r.flag)
					try{r.wait();}catch(InterruptedException e){}
				if(x==0)
				{
					r.name = "mike";
					r.sex = "nan";
				}
				else
				{
					r.name = "丽丽";
					r.sex = "女女女女女女";
				}
				r.flag = true;
				r.notify();
			}
			x = (x+1)%2;

		}
	}
}
//输出
class OutputR implements Runnable
{

	IResource r;
//	Object obj = new Object();
	OutputR(IResource r2)
	{
		this.r = r2;
	}

	public void run()
	{
		while(true)
		{
			synchronized(r)
			{
				if(!r.flag)
					try{r.wait();}catch(InterruptedException e){}
				System.out.println(r.name+"....."+r.sex);
				r.flag = false;
				r.notify();
			}
		}
	}
}



class  ResourceDemo2
{
	public static void main(String[] args) 
	{
		//创建资源。
		IResource r = new IResource();
		//创建任务。
		InputR in = new InputR(r);
		OutputR out = new OutputR(r);
		//创建线程，执行路径。
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		//开启线程
		t1.start();
		t2.start();
	}
}
