package com.haothink.thread.concurrent.callable;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * */
public class CompletionServiceTest
{
  public static void main(String[] args)
  {
    int taskSize = 5;

    ExecutorService executor = Executors.newFixedThreadPool(taskSize);

    CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
        executor);

    for (int i = 1; i <= taskSize; i++)
    {
      int sleep = taskSize - i;

      int value = i;


      completionService
          .submit(new ReturnAfterSleepCallable(sleep, value));
    }

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

    System.out.println("all over.");
    executor.shutdown();
  }
}