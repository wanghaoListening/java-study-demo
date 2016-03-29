package com.haothink.ThreadDemo.concurrent.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/**
 * "如果向Executor提交了一组计算任务，并且希望在计算完成后获得结果，
 * 那么可以保留与每个任务关联的Future，然后反复使用get方法，同时将参数timeout指定为0，
 * 从而通过轮询来判断任务是否完成。这种方法虽然可行，但却有些繁琐。幸运的是，还有一种更好的方法：
 * 完成服务CompletionService。"
 * 可见轮询future列表非常的复杂，而且还有很多异常需要处理，TimeOutException异常需要忽略；
 * 还要通过双重循环和break，防止遍历集合的过程中，出现并发修改异常。
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