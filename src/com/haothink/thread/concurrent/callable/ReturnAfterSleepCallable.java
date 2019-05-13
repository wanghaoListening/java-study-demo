package com.haothink.thread.concurrent.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/**
 * "�����Executor�ύ��һ��������񣬲���ϣ���ڼ�����ɺ��ý����
 * ��ô���Ա�����ÿ�����������Future��Ȼ�󷴸�ʹ��get������ͬʱ������timeoutָ��Ϊ0��
 * �Ӷ�ͨ����ѯ���ж������Ƿ���ɡ����ַ�����Ȼ���У���ȴ��Щ���������˵��ǣ�����һ�ָ��õķ�����
 * ��ɷ���CompletionService��"
 * �ɼ���ѯfuture�б�ǳ��ĸ��ӣ����һ��кܶ��쳣��Ҫ����TimeOutException�쳣��Ҫ���ԣ�
 * ��Ҫͨ��˫��ѭ����break����ֹ�������ϵĹ����У����ֲ����޸��쳣��
 * */
public class ReturnAfterSleepCallable implements Callable<Integer>
{
  private int sleepSeconds;

  private int returnValue;

  public ReturnAfterSleepCallable(int sleepSeconds, int returnValue)
  {
    this.sleepSeconds = sleepSeconds;
    this.returnValue = returnValue;
  }

  @Override
  public Integer call() throws Exception
  {
    System.out.println("begin to execute.");

    TimeUnit.SECONDS.sleep(sleepSeconds);

    System.out.println("end to execute.");

    return returnValue;
  }
}