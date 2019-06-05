package com.haothink.designpattern.observer.spring;

import org.springframework.context.ApplicationEvent;

/**
 * @author wanghao
 * @date 2019年05月23日 16:51
 * description: spring中的观察者模式之观察的事件
 */
public class SpringEvent extends ApplicationEvent {

    private static final long serialVersionUID = 8601878180252248791L;
    private String message;

    public SpringEvent(Object source) {
        super(source);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
