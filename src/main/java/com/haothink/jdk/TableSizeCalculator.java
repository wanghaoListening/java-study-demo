package com.haothink.jdk;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghao
 * @date 2019年07月05日 10:59
 * description:
 */
public class TableSizeCalculator {


    private static final int MAXIMUM_CAPACITY = 16;

    public static void main(String[] args) {

        Map<String,String> params = new HashMap<>(32);
        System.out.println(params);
        System.out.println(tableSizeFor(32));
    }


    /**
     * HashMap中
     * @param cap
     *        容量大小
     * @return
     */
    private static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
