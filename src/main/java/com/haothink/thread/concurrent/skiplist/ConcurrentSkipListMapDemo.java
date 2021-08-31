package com.haothink.thread.concurrent.skiplist;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by wanghao on 2021/8/31
 *
 * 跳表是一种可以用来快速查找的数据结构，有点类似于平衡树。它们都可以对元素进行快速的查找。但一个重要的区别是：
 * 对平衡树的插入和删除往往很可能导致平衡树进行一次全局的调整。而对跳表的插入和删除只需要对整个数据结构的局部进
 * 行操作即可。这样带来的好处是：在高并发的情况下，你会需要一个全局锁来保证整个平衡树的线程安全。而对于跳表，你
 * 只需要部分锁即可。这样，在高并发环境下，你就可以拥有更好的性能。而就查询的性能而言，跳表的时间复杂度也是 O(logn)
 * 所以在并发数据结构中，JDK 使用跳表来实现一个 Map。
 **/
public class ConcurrentSkipListMapDemo {

  public static void main(String[] args) {

    ConcurrentSkipListMap<String,String> concurrentSkipListMap = new ConcurrentSkipListMap<>();
  }
}
