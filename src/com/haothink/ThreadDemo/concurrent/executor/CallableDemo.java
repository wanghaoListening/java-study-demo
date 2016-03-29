package com.haothink.ThreadDemo.concurrent.executor;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * submit()方法会产生Future对象，你可以用isDone()方法查询Future任务是否已经完成
 * 当任务完成时它具有一个返回结果，可以调用get()方法来获得该结果，也可直接调用get()方法，。
 * 但可能发生线程阻塞直道结果准备就绪。
 * */
public class CallableDemo {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = 
				new ArrayList<Future<String>>();
		
		for(int i=0;i<=10;i++)
			results.add(exec.submit(new TaskResult(i)));
		
		for(Future<String> fs : results)
			try{
				System.out.println(fs.get());
			}catch(Exception e){
				System.out.println(e);
			}finally{
				exec.shutdown();
			}
	}
}

class TaskResult implements Callable<String>{
	private int id;
	public TaskResult(int id){
		this.id = id;
	}
	@Override
	public String call() throws Exception {
		
		return "result" + id;
	}
	
} 
