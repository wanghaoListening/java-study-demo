package com.haothink.thread;
/**
 * @author wanghao
 * ��demo2�������Ż�����ͬ���������ֲ��������Դ�϶����Ǿ���Ĳ�����
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

//����
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
				r.set("����","ŮŮŮŮŮŮ");
			}
			x = (x+1)%2;
		}
	}
}
//���
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
		//������Դ��
		ResourceII r = new ResourceII();
		//��������
		MYInput in = new MYInput(r);
		MyOutput out = new MyOutput(r);
		//�����̣߳�ִ��·����
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		//�����߳�
		t1.start();
		t2.start();
	}
}

