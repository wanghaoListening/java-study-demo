package com.haothink.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * Created by wanghao on 2021/9/14
 **/
public class DemoListener implements ApplicationListener<DemoEvent> {

  @Override
  public void onApplicationEvent(DemoEvent demoEvent) {

    String event = demoEvent.getMessage();
    System.out.println("接收到的事件为:"+event);

  }
}
