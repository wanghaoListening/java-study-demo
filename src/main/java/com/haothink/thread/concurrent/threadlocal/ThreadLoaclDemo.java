package com.haothink.thread.concurrent.threadlocal;


/**
 * http://www.iteye.com/topic/103804
 *  http://www.cnblogs.com/dolphin0520/p/3920407.html
 * 1��ʵ�ʵ�ͨ��ThreadLocal�����ĸ����Ǵ洢��ÿ���߳��Լ���threadLocals�еģ�
 *
 * 2��Ϊ��threadLocals������ThreadLocalMap�ļ�ֵΪThreadLocal������Ϊÿ���߳��п��ж��threadLocal������
 * ������������е�longLocal��stringLocal��
 * 3���ڽ���get֮ǰ��������set������ᱨ��ָ���쳣��
 * static class ThreadLocalMap {
 *       static class Entry extends WeakReference<ThreadLocal> {
 *           The value associated with this ThreadLocal. 
 *           Object value;
 *
 *           Entry(ThreadLocal k, Object v) {
 *               super(k);
 *               value = v;
 *           }
 *       }
 **/
public class ThreadLoaclDemo {
	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
	ThreadLocal<String> stringLocal = new ThreadLocal<String>();


	public void set() {
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}

	public long getLong() {
		return longLocal.get();
	}

	public String getString() {
		return stringLocal.get();
	}

	public static void main(String[] args) throws InterruptedException {
		final ThreadLoaclDemo test = new ThreadLoaclDemo();


		test.set();
		System.out.println(test.getLong());
		System.out.println(test.getString());


		Thread thread1 = new Thread(){
			public void run() {
				test.set();
				System.out.println(test.getLong());
				System.out.println(test.getString());
			};
		};
		thread1.start();
		thread1.join();

		System.out.println(test.getLong());
		System.out.println(test.getString());
	}
}
