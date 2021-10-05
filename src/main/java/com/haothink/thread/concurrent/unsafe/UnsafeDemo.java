package com.haothink.thread.concurrent.unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * Created by wanghao on 2021/10/5
 **/
public class UnsafeDemo {


  public static void main(String[] args) {
    try {
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      // 将字段的访问权限设置为true
      field.setAccessible(true);
      // 因为theUnsafe字段在Unsafe类中是一个静态字段，所以通过Field.get()获取字段值时，可以传null获取
      Unsafe unsafe = (Unsafe) field.get(null);
      // 控制台能打印出对象哈希码
      System.out.println(unsafe);
      //指针大小
      System.out.println(unsafe.addressSize());
      //内存页大小
      System.out.println(unsafe.pageSize());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
