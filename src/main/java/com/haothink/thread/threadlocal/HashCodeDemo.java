package com.haothink.thread.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wanghao on 2021/8/29
 **/
public class HashCodeDemo {


  private static AtomicInteger nextHashCode = new AtomicInteger();

  //斐波那契数也叫黄金分割数
  private static final int HASH_INCREMENT = 0x61c88647;

  private static int nextHashCode() {
    return nextHashCode.getAndAdd(HASH_INCREMENT);
  }

  public static void main(String[] args) {

    for(int i=0;i<10;i++) {
      System.out.println(nextHashCode()&10);
    }
  }
}
