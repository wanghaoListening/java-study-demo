package com.haothink.thread;



/*
�ȴ�/���ѻ��ơ� 

�漰�ķ�����

1��wait(): ���̴߳��ڶ���״̬����wait���̻߳ᱻ�洢���̳߳��С���Ӧ����Ҳ�ᱻ�ͷ�
2��notify():�����̳߳���һ���߳�(����).
3��notifyAll():�����̳߳��е������̡߳�
�������̵߳��ô˶���� notify() ������ notifyAll() ����ǰ�����µ�ǰ�̵߳ȴ������仰˵��
�˷�������Ϊ�ͺ�������ִ�� wait(0) ����һ���� 
��ǰ�̱߳���ӵ�д˶��������(Ҳ����������˵����������Щ���������붨����ͬ���С�)��
 

��Щ���������붨����ͬ���С�
��Ϊ��Щ���������ڲ����߳�״̬�ķ�����
����Ҫ��ȷ���ײ��������ĸ����ϵ��̡߳�


Ϊʲô�����̵߳ķ���wait notify notifyAll��������Object���У� 

��Ϊ��Щ�����Ǽ������ķ�������������ʵ��������
������������Ķ�������Ķ��󶼿��Ե��õķ���һ��������Object���С�

*/
//��Դ
class IResource
{
	String name;
	String sex;
	boolean flag = false;
}


//����
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
					r.name = "����";
					r.sex = "ŮŮŮŮŮŮ";
				}
				r.flag = true;
				r.notify();
			}
			x = (x+1)%2;

		}
	}
}
//���
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
		//������Դ��
		IResource r = new IResource();
		//��������
		InputR in = new InputR(r);
		OutputR out = new OutputR(r);
		//�����̣߳�ִ��·����
		Thread t1 = new Thread(in);
		Thread t2 = new Thread(out);
		//�����߳�
		t1.start();
		t2.start();
	}
}
