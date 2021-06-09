package com.haothink.java8;

import java.util.function.Function;

/**
 * Created by wanghao on 2021-06-09 mail:hiwanghao@didiglobal.com
 **/
public class MethodReference {


  public static void main(String[] args) {

    //静态函数引用
    Function<String,Integer> fun = Integer::parseInt;
    System.out.println(fun.apply("100"));


    //非静态函数引用
    String content = "hello jdk8";

    Function<Integer,String> fun2 = content::substring;
    System.out.println(fun2.apply(2));

    //
  }




}
