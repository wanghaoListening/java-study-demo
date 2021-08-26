package com.haothink.thread.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wanghao on 2021/8/26
 **/
public class NamingThreadFactory implements ThreadFactory {

  private final AtomicInteger threadNum = new AtomicInteger();
  private final ThreadFactory delegate;
  private final String name;

  /**
   * 创建一个带名字的线程池生产工厂
   */
  public NamingThreadFactory(ThreadFactory delegate, String name) {
    this.delegate = delegate;
    this.name = name; // TODO consider uniquifying this
  }

  @Override
  public Thread newThread(Runnable r) {
    Thread t = delegate.newThread(r);
    t.setName(name + " [#" + threadNum.incrementAndGet() + "]");
    return t;
  }
}
