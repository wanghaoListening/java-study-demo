package com.haothink.guide.generics;



import java.util.ArrayList;

/**
 * @author wanghao
 * @date 2019年08月16日 15:20
 * description: 泛型擦除
 */
public class GenericErasure {

    public static void main(String[] args) {

        Class clazz1 = new ArrayList<String>().getClass();
        Class clazz2 = new ArrayList<Integer>().getClass();

        System.out.println(clazz1 == clazz2);
    }
}
