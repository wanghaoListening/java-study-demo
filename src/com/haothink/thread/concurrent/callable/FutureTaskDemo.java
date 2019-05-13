package com.haothink.thread.concurrent.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
/**
 * ����������һ��FutureTask��ʵ�֣�
1
	
public class FutureTask<V> implements RunnableFuture<V>

 ����FutureTask��ʵ����RunnableFuture�ӿڣ����ǿ�һ��RunnableFuture�ӿڵ�ʵ�֣�

public interface RunnableFuture<V> extends Runnable, Future<V> {
    void run();
}

 �������Կ���RunnableFuture�̳���Runnable�ӿں�Future�ӿڣ���FutureTaskʵ����RunnableFuture�ӿڡ��������ȿ�����ΪRunnable���߳�ִ�У��ֿ�����ΪFuture�õ�Callable�ķ���ֵ��

����FutureTask�ṩ��2����������

public FutureTask(Callable<V> callable) {
}
public FutureTask(Runnable runnable, V result) {
}

������ʵ�ϣ�FutureTask��Future�ӿڵ�һ��Ψһʵ���ࡣ
 * */
public class FutureTaskDemo {
	 public static void main(String[] args) {
	        //��һ�ַ�ʽ
	        ExecutorService executor = Executors.newCachedThreadPool();
	        Task task = new Task();
	        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
	        executor.submit(futureTask);
	        executor.shutdown();
	         
	        //�ڶ��ַ�ʽ��ע�����ַ�ʽ�͵�һ�ַ�ʽЧ�������Ƶģ�ֻ����һ��ʹ�õ���ExecutorService��һ��ʹ�õ���Thread
	        /*Task task = new Task();
	        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
	        Thread thread = new Thread(futureTask);
	        thread.start();*/
	         
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e1) {
	            e1.printStackTrace();
	        }
	         
	        System.out.println("���߳���ִ������");
	         
	        try {
	            System.out.println("task���н��"+futureTask.get());
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } catch (ExecutionException e) {
	            e.printStackTrace();
	        }
	         
	        System.out.println("��������ִ�����");
	    }
	}
	class Task implements Callable<Integer>{
	    @Override
	    public Integer call() throws Exception {
	        System.out.println("���߳��ڽ��м���");
	        Thread.sleep(3000);
	        int sum = 0;
	        for(int i=0;i<100;i++)
	            sum += i;
	        return sum;
	    }
	}