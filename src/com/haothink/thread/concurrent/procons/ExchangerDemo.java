package com.haothink.thread.concurrent.procons;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * �����ڶ��ж�Ԫ�ؽ�����Ժͽ������̵߳�ͬ���㡣ÿ���߳̽���Ŀ�ϵ�ĳ���������ָ� exchange ������
 * �����߳̽���ƥ�䣬�����ڷ���ʱ��������Ķ���Exchanger���ܱ���Ϊ SynchronousQueue
 * ��˫����ʽ��
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
					System.out.println("�߳�" + Thread.currentThread().getName() + "���ڰ�����" + data1 + "����ȥ");
					Thread.sleep((long) (Math.random() * 1000));
					String data2 = exchanger.exchange(data1);
					System.out.println("�߳�" + Thread.currentThread().getName() + "���ص�����Ϊ" + data2);
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
					System.out.println("�߳�" + Thread.currentThread().getName() + "���ڰ�����" + data1 + "����ȥ");
					Thread.sleep((long) (Math.random() * 1000));
					String data2 = exchanger.exchange(data1);
					System.out.println("�߳�" + Thread.currentThread().getName() + "���ص�����Ϊ" + data2);
				}
				catch (Exception e)
				{
				}
			}
		});
	}
}

