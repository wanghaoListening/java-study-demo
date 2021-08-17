package com.haothink.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wanghao on 2021/8/17
 **/
public class LazySetDemo {

  public static void main(String[] args) {

    AtomicInteger atomicInteger = new AtomicInteger(10);
    //以普通变量的形式来读写变量,不去设置内存屏障
    atomicInteger.lazySet(20);
    System.out.println(atomicInteger);
  }
}
