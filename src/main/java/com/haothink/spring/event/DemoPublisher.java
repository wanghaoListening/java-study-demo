package com.haothink.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Created by wanghao on 2021/9/14
 **/
public class DemoPublisher {

  @Autowired
  ApplicationContext applicationContext;

  public void publish(String message){
    //发布事件
    applicationContext.publishEvent(new DemoEvent(this, message));
  }
}
