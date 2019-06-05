package com.haothink.classloading;

/**
 * @author wanghao
 * @date 2019年06月05日 15:32
 * description:
 */
class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;
}

class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init!");
    }
}
