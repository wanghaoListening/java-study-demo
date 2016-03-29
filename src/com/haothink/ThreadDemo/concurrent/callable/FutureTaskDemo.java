package com.haothink.ThreadDemo.concurrent.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
/**
 * 我们先来看一下FutureTask的实现：
1
	
public class FutureTask<V> implements RunnableFuture<V>

 　　FutureTask类实现了RunnableFuture接口，我们看一下RunnableFuture接口的实现：

public interface RunnableFuture<V> extends Runnable, Future<V> {
    void run();
}

 　　可以看出RunnableFuture继承了Runnable接口和Future接口，而FutureTask实现了RunnableFuture接口。所以它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值。

　　FutureTask提供了2个构造器：

public FutureTask(Callable<V> callable) {
}
public FutureTask(Runnable runnable, V result) {
}

　　事实上，FutureTask是Future接口的一个唯一实现类。
 * */
public class FutureTaskDemo {
	 public static void main(String[] args) {
	        //第一种方式
	        ExecutorService executor = Executors.newCachedThreadPool();
	        Task task = new Task();
	        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
	        executor.submit(futureTask);
	        executor.shutdown();
	         
	        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
	        /*Task task = new Task();
	        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
	        Thread thread = new Thread(futureTask);
	        thread.start();*/
	         
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e1) {
	            e1.printStackTrace();
	        }
	         
	        System.out.println("主线程在执行任务");
	         
	        try {
	            System.out.println("task运行结果"+futureTask.get());
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } catch (ExecutionException e) {
	            e.printStackTrace();
	        }
	         
	        System.out.println("所有任务执行完毕");
	    }
	}
	class Task implements Callable<Integer>{
	    @Override
	    public Integer call() throws Exception {
	        System.out.println("子线程在进行计算");
	        Thread.sleep(3000);
	        int sum = 0;
	        for(int i=0;i<100;i++)
	            sum += i;
	        return sum;
	    }
	}