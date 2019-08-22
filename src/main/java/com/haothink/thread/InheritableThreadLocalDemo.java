package com.haothink.thread;

/**
 * @author wanghao
 * @date 2019年08月22日 19:10
 * description: InheritableThreadLocal
 *
 *
 */
public class InheritableThreadLocalDemo {


    public static void main(String[] args) {

        final ThreadLocal<Object> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("that's ok");
        new Thread(()->{
            System.out.println(threadLocal.get());
        }).start();
    }
}
