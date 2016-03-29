package com.haothink.ThreadDemo;
/**
 * @author wanghao
 * 对demo2进行了优化，将同步代码块移植到共享资源上而不是具体的操作上
 * */
class ResourceII
{
	private String name;
	private String sex;
	private boolean flag = false;

	public synchronized void set(String name,String sex)
	{
		if(flag)
			try{this.wait();}catch(InterruptedException e){}
		this.name = name;
		this.sex = sex;
		flag = true;
		this.notify();
	}

	public synchronized void out()
	{
		if(!flag)
			try{this.wait();}catch(InterruptedException e){}
		System.out.println(name+"...+...."+sex);
		flag = false;
		notify();
	}
}

//输入
class MYInput implements Runnable
{
	ResourceII r ;
//	Object obj = new Object();
	MYInput(ResourceII r)
	{
		this.r = r;
	}
	public void run()
	{
		int x = 0;
		while(true)
		{
			if(x==0)
			{
				r.set("mike","nan");
			}
			else
			{
				r.set("丽丽","女女女女女女");
			}
			x = (x+1)%2;
		}
	}
}
//输出
class MyOutput implements Runnable
{

	ResourceII r;
//	Object obj = new Object();
	MyOutput(ResourceII r)
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



 public class ResourceDemo3
{
	public static void main(String[] args) 
	{
		//创建资源。
		ResourceII r = new ResourceII();
		//创建任务。
		MYInput in = new MYInput(r);
		MyOutput out = new MyOutput(r);
		//创建线程，执行路径。
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		//开启线程
		t1.start();
		t2.start();
	}
}

