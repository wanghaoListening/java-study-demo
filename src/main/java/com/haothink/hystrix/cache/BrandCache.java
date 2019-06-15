package com.haothink.hystrix.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghao
 * @date 2019年06月15日 17:12
 * description: 品牌名称本地缓存
 */
public class BrandCache {


    private static Map<Long, String> brandMap = new HashMap<>();

    static {
        brandMap.put(1L, "Nike");
    }

    /**
     * brandId 获取 brandName
     *
     * @param brandId 品牌id
     * @return 品牌名
     */
    public static String getBrandName(Long brandId) {
        return brandMap.get(brandId);
    }

}
