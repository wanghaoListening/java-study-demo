package com.haothink.ThreadDemo;

/**
 * 即使是创建了两个Test对象，但static final 修饰的所只有一份
 * 所以在执行时发生死锁现象。
 * */
class Test implements Runnable
{
	private boolean flag;
	Test(boolean flag)
	{
		this.flag = flag;
	}

	public void run()
	{
		
		if(flag)
		{
			while(true)
				synchronized(MyLock.locka)
				{
					System.out.println(Thread.currentThread().getName()+"..if   locka....");
					synchronized(MyLock.lockb)				{
						
						System.out.println(Thread.currentThread().getName()+"..if   lockb....");
					}
				}
		}
		else
		{
			while(true)			
				synchronized(MyLock.lockb)
				{
					System.out.println(Thread.currentThread().getName()+"..else  lockb....");
					synchronized(MyLock.locka)
					{
						System.out.println(Thread.currentThread().getName()+"..else   locka....");
					}
				}
		}

	}

}

class MyLock
{
	public static final Object locka = new Object();
	public static final Object lockb = new Object();
}




class DeadLockTest 
{
	public static void main(String[] args) 
	{
		Test a = new Test(true);
		Test b = new Test(false);
		
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);
		t1.start();
		t2.start();
	}
}
