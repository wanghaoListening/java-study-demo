package com.haothink.designpattern.observer.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author wanghao
 * @date 2019年05月23日 16:56
 * description:spring中的观察者模式之事件发布者
 *
 * 深入publishEvent 方法，最终发现在multicastEvent对注册的监听者进行调用
 *
 * public void multicastEvent(final ApplicationEvent event, ResolvableType eventType) {
 *         ResolvableType type = eventType != null ? eventType : this.resolveDefaultEventType(event);
 *         Iterator var4 = this.getApplicationListeners(event, type).iterator();
 *
 *         while(var4.hasNext()) {
 *             final ApplicationListener<?> listener = (ApplicationListener)var4.next();
 *             Executor executor = this.getTaskExecutor();
 *             if (executor != null) {
 *                 executor.execute(new Runnable() {
 *                     public void run() {
 *                         SimpleApplicationEventMulticaster.this.invokeListener(listener, event);
 *                     }
 *                 });
 *             } else {
 *                 this.invokeListener(listener, event);
 *             }
 *         }
 *
 *     }
 */
@Component
public class SpringPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    public void publish(String message){
        //发布事件
        applicationContext.publishEvent(new SpringEvent(message));
    }
}
