package com.haothink.single;

/**
 * Created by wanghao on 2021/8/18
 **/
public class SingleClass {

  private SingleClass(){}

  private static volatile SingleClass singleClass = null;


  public static SingleClass getInstance(){

    if(singleClass == null) {
      synchronized (SingleClass.class) {
        if (singleClass == null) {
          singleClass = new SingleClass();
          return singleClass;
        }
        return singleClass;
      }
    }
    return singleClass;
  }
}
