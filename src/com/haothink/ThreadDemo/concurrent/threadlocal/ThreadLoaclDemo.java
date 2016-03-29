package com.haothink.ThreadDemo.concurrent.threadlocal;


/**
 * http://www.iteye.com/topic/103804
 *  http://www.cnblogs.com/dolphin0520/p/3920407.html
 * 1）实际的通过ThreadLocal创建的副本是存储在每个线程自己的threadLocals中的；
 *
 * 2）为何threadLocals的类型ThreadLocalMap的键值为ThreadLocal对象，因为每个线程中可有多个threadLocal变量，
 * 就像上面代码中的longLocal和stringLocal；
 * 3）在进行get之前，必须先set，否则会报空指针异常；
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
