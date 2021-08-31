package com.haothink.thread.threadlocal;

/**
 * Created by wanghao on 2021/8/29
 * 我们使用ThreadLocal的时候，在异步场景下是无法给子线程共享父线程中创建的线程副本数据的。
 * 为了解决这个问题，JDK 中还有一个InheritableThreadLocal类，我们来看一个例子：
 **/
public class InheritableThreadLocalDemo {

  public static void main(String[] args) {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();
    ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    threadLocal.set("父类数据:threadLocal");
    inheritableThreadLocal.set("父类数据:inheritableThreadLocal");

    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("子线程获取父类ThreadLocal数据：" + threadLocal.get());
        System.out.println("子线程获取父类inheritableThreadLocal数据：" + inheritableThreadLocal.get());
      }
    }).start();
  }
}
