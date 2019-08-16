package com.haothink.guide.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 * @date 2019年08月16日 15:34
 * description: 泛型反射
 */
public class GenericReflection {


    public static void main(String[] args) throws Exception {

        addEleInList();
    }


    private static void addEleInList() throws Exception {

        List<Integer> list = new ArrayList<>();

        list.add(1);

        list.getClass().getMethod("add",Object.class).invoke(list,"abc");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }
}
