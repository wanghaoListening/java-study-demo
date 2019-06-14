package com.haothink.hystrix.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghao
 * @date 2019年06月14日 19:06
 * description:
 */
public class LocationCache {

    private static Map<Long, String> cityMap = new HashMap<>();

    static {
        cityMap.put(1L, "北京");
    }

    /**
     * 通过cityId 获取 cityName
     *
     * @param cityId 城市id
     * @return 城市名
     */
    public static String getCityName(Long cityId) {
        return cityMap.get(cityId);
    }
}
