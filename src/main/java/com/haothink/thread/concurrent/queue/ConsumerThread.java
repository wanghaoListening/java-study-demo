package com.haothink.thread.concurrent.queue;

import java.util.Calendar;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by wanghao on 2021/8/31
 **/
public class ConsumerThread implements Runnable {
  private String name;
  private ConcurrentLinkedQueue<String> queue;
  private volatile boolean flag = true;

  public ConsumerThread(String name, ConcurrentLinkedQueue<String> queue) {
    this.name = name;
    this.queue = queue;
  }

  @Override
  public void run() {
    System.out.println(now() + this.name + "：线程启动。");
    try {
      while (flag) {
        System.out.println(now() + this.name + "：正在从队列中获取数据......");
        String data = queue.poll();
        System.out.println(now() + this.name + "：拿到队列中的数据：" + data);
        //等待1秒钟
        Thread.sleep(1000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      System.out.println(now() + this.name + "：消退出线程。");
    }
  }

  // 获取当前时间（分:秒）
  public String now() {
    Calendar now = Calendar.getInstance();
    return "[" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) + "] ";
  }
}
