package com.haothink.single;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wanghao on 2021/8/18 mail:hiwanghao@didiglobal.com
 **/
public class CountDownLatchDemo {



  public static void main(String[] args) throws InterruptedException {

    CountDownLatch countDownLatch = new CountDownLatch(1);
    for (int i=0;i<10;i++){
      Task t = new Task(countDownLatch);
      new Thread(t).start();
    }
    countDownLatch.countDown();
    System.out.println("main:");
  }


  public static class Task implements Runnable{

    private CountDownLatch countDownLatch;

    public Task(CountDownLatch countDownLatch) {
      this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
      try {
        countDownLatch.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
