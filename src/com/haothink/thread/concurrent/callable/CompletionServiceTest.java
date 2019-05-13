package com.haothink.thread.concurrent.callable;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * CompletionServiceʵ�����������ύ����������߻�ȡ����Ľ��
 * �����ߺ������߶����ù�����������˳���� CompletionService����֤��
 * ������һ���ǰ���������ɵ��Ⱥ�˳������ȡִ�н����
 * http://www.tuicool.com/articles/umyy6b
 * ExecutorCompletionService��CompletionService��ʵ�֣�
 * �ں����̳߳�Executor����������BlockingQueue�Ĺ��ܡ�
 * http://xw-z1985.iteye.com/blog/1997077
 * */
public class CompletionServiceTest
{
  public static void main(String[] args)
  {
    int taskSize = 5;

    ExecutorService executor = Executors.newFixedThreadPool(taskSize);

    // ������ɷ���
    CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
        executor);

    for (int i = 1; i <= taskSize; i++)
    {
      int sleep = taskSize - i; // ˯��ʱ��

      int value = i; // ���ؽ��

      // ���̳߳��ύ����
      completionService
          .submit(new ReturnAfterSleepCallable(sleep, value));
    }

    // �������˳��,��ӡ���
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

    // ���������Ѿ����,�ر��̳߳�
    System.out.println("all over.");
    executor.shutdown();
  }
}