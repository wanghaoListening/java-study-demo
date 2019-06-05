package com.haothink.designpattern.observer.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author wanghao
 * @date 2019年05月23日 16:54
 * description: spring中的观察者模式之监听者
 * 定义一个事件监听者,实现ApplicationListener接口，重写 onApplicationEvent() 方法
 */
@Component
public class SpringListener implements ApplicationListener<SpringEvent> {


    @Override
    public void onApplicationEvent(SpringEvent springEvent) {
        //从事件中拉取消息
        String msg = springEvent.getMessage();
        System.out.println("接收到的信息是："+msg);
    }
}
