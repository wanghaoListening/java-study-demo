package com.haothink.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by wanghao on 2021/9/14
 **/
public class DemoEvent extends ApplicationEvent {

  private static final long serialVersionUID = 1L;

  private String message;

  public DemoEvent(Object source,String message){
    super(source);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
