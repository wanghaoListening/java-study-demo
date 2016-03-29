package com.haothink.ThreadDemo.concurrent.callable;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * CompletionService实现了生产者提交任务和消费者获取结果的解耦，
 * 生产者和消费者都不用关心任务的完成顺序，由 CompletionService来保证，
 * 消费者一定是按照任务完成的先后顺序来获取执行结果。
 * http://www.tuicool.com/articles/umyy6b
 * ExecutorCompletionService是CompletionService的实现，
 * 融合了线程池Executor和阻塞队列BlockingQueue的功能。
 * http://xw-z1985.iteye.com/blog/1997077
 * */
public class CompletionServiceTest
{
  public static void main(String[] args)
  {
    int taskSize = 5;

    ExecutorService executor = Executors.newFixedThreadPool(taskSize);

    // 构建完成服务
    CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
        executor);

    for (int i = 1; i <= taskSize; i++)
    {
      int sleep = taskSize - i; // 睡眠时间

      int value = i; // 返回结果

      // 向线程池提交任务
      completionService
          .submit(new ReturnAfterSleepCallable(sleep, value));
    }

    // 按照完成顺序,打印结果
    for (int i = 0; i < taskSize; i++)
    {
      try
      {
        System.out.println(completionService.take().get());
      } catch (InterruptedException e)
      {
        e.printStackTrace();
      } catch (ExecutionException e)
      {
        e.printStackTrace();
      }
    }

    // 所有任务已经完成,关闭线程池
    System.out.println("all over.");
    executor.shutdown();
  }
}