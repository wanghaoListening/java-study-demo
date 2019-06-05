package com.haothink.thread.concurrent.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class TraditionalTest
{
  public static void main(String[] args)
  {
    int taskSize = 5;

    ExecutorService executor = Executors.newFixedThreadPool(taskSize);

    List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();

    for (int i = 1; i <= taskSize; i++)
    {
      int sleep = taskSize - i;

      int value = i;

      Future<Integer> future = executor.submit(new ReturnAfterSleepCallable(sleep, value));

      futureList.add(future);
    }

    while(taskSize > 0)
    {
      for (Future<Integer> future : futureList)
      {
        Integer result = null;
        
        try
        {
          result = future.get(0, TimeUnit.SECONDS);
        } catch (InterruptedException e)
        {
          e.printStackTrace();
        } catch (ExecutionException e)
        {
          e.printStackTrace();
        } catch (TimeoutException e)
        {

        }

        if(result != null)
        {
          System.out.println("result=" + result);

          futureList.remove(future);  
          taskSize--;
          break;
        }
      }
    }

    System.out.println("all over.");
    executor.shutdown();
  }
  
  
  
}