package com.haothink.guide.generics;

/**
 * @author wanghao
 * @date 2019年08月16日 15:13
 * description: 利用泛型实现tuple类型
 */
public class JavaTuple<A,B,C>{

    private A a;
    private B b;
    private C c;

    public JavaTuple(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
