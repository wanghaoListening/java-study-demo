package com.haothink.thread.concurrent.executor;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * submit()���������Future�����������isDone()������ѯFuture�����Ƿ��Ѿ����
 * ���������ʱ������һ�����ؽ�������Ե���get()��������øý����Ҳ��ֱ�ӵ���get()��������
 * �����ܷ����߳�����ֱ�����׼��������
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
