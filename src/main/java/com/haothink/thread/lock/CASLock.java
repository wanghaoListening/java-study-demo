package com.haothink.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wanghao on 2021/8/17
 **/
public class CASLock {

  AtomicInteger atomicInteger = new AtomicInteger();
  Thread currentThread = null;

  public void tryLock() throws Exception {

    boolean isLock = atomicInteger.compareAndSet(0,1);
    if(!isLock){
      throw new Exception("加锁失败");
    }
    currentThread = Thread.currentThread();
    System.out.println(currentThread+" tryLock");
  }

  public void unlock(){

    int lockValue = atomicInteger.get();
    if(lockValue == 0){
      return;
    }
    if(currentThread == Thread.currentThread()){
      atomicInteger.compareAndSet(1,0);
      System.out.println(currentThread + " unlock");
    }
  }
}
