package com.haothink.ThreadDemo.concurrent.procons;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 可以在队中对元素进行配对和交换的线程的同步点。每个线程将条目上的某个方法呈现给 exchange 方法，
 * 与伙伴线程进行匹配，并且在返回时接收其伙伴的对象。Exchanger可能被视为 SynchronousQueue
 * 的双向形式。
 * */
public class ExchangerDemo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		final Exchanger<String> exchanger = new Exchanger<String>();

		exec.execute(new Runnable(){

			@Override
			public void run() {
				try{
					String data1 = "book";
					System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data1 + "换出去");
					Thread.sleep((long) (Math.random() * 1000));
					String data2 = exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为" + data2);
				}
				catch (Exception e)
				{
				}

			}

		});

		exec.execute(new Runnable()
		{
			public void run()
			{
				try
				{
					String data1 = "money";
					System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data1 + "换出去");
					Thread.sleep((long) (Math.random() * 1000));
					String data2 = exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为" + data2);
				}
				catch (Exception e)
				{
				}
			}
		});
	}
}

