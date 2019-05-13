package com.haothink.thread;

/*
	ֹͣ�̣߳�
	1��stop������	
	2��run����������	
	��ô�����̵߳����������?
	�����ж�����ѭ���ṹ��ֻҪ����סѭ���Ϳ��Խ�������	
	����ѭ��ͨ�����ö���������ɡ�
	
	��������̴߳����˶���״̬���޷���ȡ��ǡ���ν����أ�
	
	����ʹ��interrupt()�������̴߳Ӷ���״̬ǿ�ƻָ�������״̬���������߳̾߱�cpu��ִ���ʸ� 
	
	��ʱǿ�ƶ����ᷢ����InterruptedException���ǵ�Ҫ����

*/
class StopThread implements Runnable
{
	private boolean flag = true;
	public synchronized void run()
	{
		while(flag)
		{
			try
			{
				wait();//t0 t1
			}
			catch (InterruptedException e)
			{
				System.out.println(Thread.currentThread().getName()+"....."+e);
				flag = false;
			}
			
			System.out.println(Thread.currentThread().getName()+"......++++");
		}
	}
	public void setFlag()
	{
		flag = false;
	}
}


class InterruptDemo 
{
	public static void main(String[] args) 
	{
		StopThread st = new StopThread();

		Thread t1 = new Thread(st);
		Thread t2 = new Thread(st);

		t1.start();
		//t2.setDaemon(true);
		t2.start();


		int num = 1;
		for(;;)
		{
			if(++num==50)
			{
			    st.setFlag();
				t1.interrupt();
 			    t2.interrupt();
				break;
			}
			System.out.println("main...."+num);
		}

		System.out.println("over");
	}
}
