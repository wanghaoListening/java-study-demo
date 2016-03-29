package com.haothink.ThreadDemo;


/*
线程间通讯：
多个线程在处理同一资源，但是任务却不同。
前提：多个线程对统一资源进行操作都需要同步锁，而且是同一把锁

*/

//资源
class Resource
{
	String name;
	String sex;
}


//输入
class Input implements Runnable
{
	IResource r ;
//	Object obj = new Object();
	Input(IResource r)
	{
		this.r = r;
	}
	public void run()
	{
		int x = 0;
		while(true)
		{
			synchronized(r)
			{
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
			}
			x = (x+1)%2;

		}
	}
}
//输出
class Output implements Runnable
{

	IResource r;
//	Object obj = new Object();
	Output(IResource r)
	{
		this.r = r;
	}

	public void run()
	{
		while(true)
		{
			synchronized(r)
			{
				System.out.println(r.name+"....."+r.sex);
			}
		}
	}
}



class  ResourceDemo
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
