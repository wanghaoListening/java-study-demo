package com.haothink.ThreadDemo;
//死锁：常见情景之一：同步的嵌套。
/**
 * 当去掉try{Thread.sleep(10);}catch(InterruptedException e){}
 * 两线程交叉执行并未发生死锁：自我猜测应该是jvm内部做了很好的优化
 * 当加上Thread.sleep(10)时死锁现象出现了。
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
