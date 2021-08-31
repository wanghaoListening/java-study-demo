package com.haothink.thread.concurrent.queue;

import java.util.Calendar;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wanghao on 2021/8/31
 **/
public class ProducerThread implements Runnable {
  private String name;
  private ConcurrentLinkedQueue<String> queue;
  private volatile boolean flag = true;
  private static AtomicInteger count = new AtomicInteger();

  public ProducerThread(String name, ConcurrentLinkedQueue<String> queue) {
    this.name = name;
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      System.out.println(now() + this.name + "：线程启动。");
      while (flag) {
        String data = count.incrementAndGet()+"";
        // 将数据存入队列中
        queue.offer(data);
        System.out.println(now() + this.name + "：存入" + data + "到队列中。");
        //等待1秒钟
        Thread.sleep(1000);
      }
    } catch (Exception e) {

    } finally {
      System.out.println(now() + this.name + "：退出线程。");
    }
  }

  public void stopThread() {
    this.flag = false;
  }

  // 获取当前时间（分:秒）
  public String now() {
    Calendar now = Calendar.getInstance();
    return "[" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) + "] ";
  }
}
