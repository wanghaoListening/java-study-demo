package com.haothink.ThreadDemo.concurrent.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * java.uitl.concurrent.ThreadPoolExecutor类是线程池中最核心的一个类
 * 　ArrayBlockingQueue和PriorityBlockingQueue使用较少，
* 一般使用LinkedBlockingQueue和Synchronous。线程池的排队策略与BlockingQueue有关。
*     handler：表示当拒绝处理任务时的策略，有以下四种取值：

	ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。 
	ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。 
	ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
	ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务 
	1.public class ThreadPoolExecutor extends AbstractExecutorService
	2.public abstract class AbstractExecutorService implements ExecutorService
	3.public interface ExecutorService extends Executor {
	public interface Executor {
    void execute(Runnable command);
	}
	　　在ThreadPoolExecutor类中有几个非常重要的方法
	
		execute()
		submit()
		shutdown()
		shutdownNow()
*1）首先，要清楚corePoolSize和maximumPoolSize的含义；
*
*　　2）其次，要知道Worker是用来起到什么作用的；
*
*　　3）要知道任务提交给线程池之后的处理策略，这里总结一下主要有4点：
*
*   如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
*    如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，
*    若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），
*    则会尝试创建新的线程去执行这个任务；
*    如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
*    如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，
*    线程将被终止，直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，
*   那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。
*	5.任务拒绝策略

　　当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略，
通常有以下四种策略：
ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务 
*
 * */
public class ThreadPoolDemo {
	
	 public static void main(String[] args) {   
         ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                 new ArrayBlockingQueue<Runnable>(5));
          
         for(int i=0;i<15;i++){
             MyTask myTask = new MyTask(i);
             executor.execute(myTask);
             System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
             executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
         }
         executor.shutdown();
     }
}
 
 
class MyTask implements Runnable {
    private int taskNum;
     
    public MyTask(int num) {
        this.taskNum = num;
    }
     
    @SuppressWarnings("static-access")
	@Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}