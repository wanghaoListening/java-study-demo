package com.haothink.thread.concurrent.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * Created by wanghao on 2021/8/31
 * AtomicMarkableReference是将一个boolean值作是否有更改的标记，本质就是它的版本号只有两个，true和false，
 * 修改的时候在这两个版本号之间来回切换，这样做并不能解决ABA的问题，只是会降低ABA问题发生的几率而已
 **/
public class AtomicMarkableReferenceDemo {

  private static AtomicMarkableReference atomicMarkableReference = new AtomicMarkableReference(100, false);

  public static void main(String[] args) {

    Thread refT1 = new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      atomicMarkableReference.compareAndSet(100, 101, atomicMarkableReference.isMarked(), !atomicMarkableReference.isMarked());
      atomicMarkableReference.compareAndSet(101, 100, atomicMarkableReference.isMarked(), !atomicMarkableReference.isMarked());
    });

    Thread refT2 = new Thread(() -> {
      boolean marked = atomicMarkableReference.isMarked();
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      boolean c3 = atomicMarkableReference.compareAndSet(100, 101, marked, !marked);
      System.out.println(c3); // 返回true,实际应该返回false
    });

    refT1.start();
    refT2.start();
  }

}
