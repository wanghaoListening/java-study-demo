package com.haothink.thread.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;



/**
 * Created by wanghao on 2021/8/26
 **/
public class ThreadPoolMetric {

  private static String threadNamePrefix;

  public static void main(String[] args) {

    ThreadFactory threadFactory = new ThreadFactoryBuilder()
        .setNameFormat(threadNamePrefix + "-%d")
        .setDaemon(true).build();

  }

  /**
   * 打印线程池的状态
   *
   * @param threadPool 线程池对象
   */
  public static void printThreadPoolStatus(ThreadPoolExecutor threadPool) {

    System.out.println("=========================");
    System.out.println("ThreadPool Size: [{}]"+threadPool.getPoolSize());
    System.out.println("Active Threads: {}"+threadPool.getActiveCount());
    System.out.println("Number of Tasks : {}"+threadPool.getCompletedTaskCount());
    System.out.println("Number of Tasks in Queue: {}"+threadPool.getQueue().size());
    System.out.println("=========================");
  }
}
