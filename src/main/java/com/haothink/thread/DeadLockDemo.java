package com.haothink.thread;
//�����������龰֮һ��ͬ����Ƕ�ס�
/**
 * ��ȥ��try{Thread.sleep(10);}catch(InterruptedException e){}
 * ���߳̽���ִ�в�δ�������������Ҳ²�Ӧ����jvm�ڲ����˺ܺõ��Ż�
 * ������Thread.sleep(10)ʱ������������ˡ�
 * */
public class DeadLockDemo {
	
	public static void main(String[] args) {
		Ticket t = new Ticket();
		
		new Thread(t).start();
		try{Thread.sleep(10);}catch(InterruptedException e){}
		t.flag = false;
		new Thread(t).start();
	}
	
}

class Ticket implements Runnable{
	private  int num = 100;
	Object obj = new Object();
	boolean flag = true;

	@Override
	public void run() {
		if(flag)
			while(true)
			{
				synchronized(obj)
				{
					show();
				}
			}
		else
			while(true)
				this.show();

	}

	public synchronized void show()
	{

		synchronized(obj)
		{
			if(num>0)
			{
				try{Thread.sleep(10);}catch (InterruptedException e){}

				System.out.println(Thread.currentThread().getName()+".....sale...."+num--);
			}
		}
	}

}
