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
      int sleep = taskSize - i; // ˯��ʱ��

      int value = i; // ���ؽ��

      // ���̳߳��ύ����
      Future<Integer> future = executor.submit(new ReturnAfterSleepCallable(sleep, value));
      
      // ����ÿ�������Future
      futureList.add(future);
    }
    
    // ��ѯ,��ȡ�������ķ��ؽ��
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
          // ��ʱ�쳣��Ҫ����,��Ϊ���������˵ȴ�ʱ��Ϊ0,ֻҪ����û�����,�ͻᱨ���쳣
        }
        
        // �����Ѿ����
        if(result != null)
        {
          System.out.println("result=" + result);
          
          // ��future�б���ɾ���Ѿ���ɵ�����
          futureList.remove(future);  
          taskSize--;
          //�˴�����break��������׳������޸��쳣����Ҳ����ͨ����futureList����ΪCopyOnWriteArrayList���ͽ���� 
          break; // ������һ��whileѭ��
        }
      }
    }
    
    // ���������Ѿ����,�ر��̳߳�
    System.out.println("all over.");
    executor.shutdown();
  }
  
  
  
}