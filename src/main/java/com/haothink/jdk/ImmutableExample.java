package com.haothink.jdk;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghao
 * @date 2019年07月08日 16:36
 * description:
 */
public class ImmutableExample {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(map);
        unmodifiableMap.put("a", 1);
    }
}
