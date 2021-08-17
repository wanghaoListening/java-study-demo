package com.haothink.collection;

import java.util.ArrayList;

/**
 * Created by wanghao on 2021/8/16
 **/
public class EnsureCapacityTest {

  public static void main(String[] args) {
    ArrayList<Object> list = new ArrayList<Object>();
    final int N = 10000000;
    list = new ArrayList<Object>();
    long startTime1 = System.currentTimeMillis();
    list.ensureCapacity(N);
    for (int i = 0; i < N; i++) {
      list.add(i);
    }
    long endTime1 = System.currentTimeMillis();
    System.out.println("使用ensureCapacity方法后："+(endTime1 - startTime1));
  }

}