package com.haothink.ThreadDemo.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 1.SingleThreadExecutor是线程数为1的FixedThreadExecutor
 * 2.向SingleThreadExecutor提交多个任务，那么这些任务将排队
 * 等待直道前一个任务结束，他会维护自己的悬挂任务队列（隐藏）
 * 
 * 
 * */
public class SingleThreadDemo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		exec.execute(new Task());
		exec.execute(new Task());
		exec.execute(new Task());
	}
	
	
}


class Task implements Runnable{
	private int i = 1000000;
	@Override
	public void run() {
		while(true){
			synchronized (this) {
				if(i>=0)
				System.out.println(Thread.currentThread().getName()+".."+(i--));
			}
		}
		
	}
	
}