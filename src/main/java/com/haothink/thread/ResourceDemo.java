package com.haothink.thread;


/*
�̼߳�ͨѶ��
����߳��ڴ���ͬһ��Դ����������ȴ��ͬ��
ǰ�᣺����̶߳�ͳһ��Դ���в�������Ҫͬ������������ͬһ����

*/

//��Դ
class Resource
{
	String name;
	String sex;
}


//����
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
					r.name = "����";
					r.sex = "ŮŮŮŮŮŮ";
				}
			}
			x = (x+1)%2;

		}
	}
}
//���
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
