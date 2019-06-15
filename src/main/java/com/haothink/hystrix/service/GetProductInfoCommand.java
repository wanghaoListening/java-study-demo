package com.haothink.hystrix.service;


import com.alibaba.fastjson.JSONObject;
import com.haothink.hystrix.BO.ProductInfoBO;
import com.haothink.utils.HttpClientUtils;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

import java.util.HashMap;
import java.util.Map;


/**
 * @author wanghao
 * @date 2019年06月13日 20:29
 * description:
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfoBO> {


    private static final HystrixCommandKey KEY = HystrixCommandKey.Factory.asKey("GetProductInfoCommand");

    private Long productId;

    public GetProductInfoCommand(Long productId) {
        //默认使用线程池进行隔离
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoCommandGroup"));
        this.productId = productId;
    }

    @Override
    protected ProductInfoBO run() throws Exception {
        String url = "http://localhost:8081/getProductInfo";
        // 调用商品服务接口
        Map<String,Object> params = new HashMap<>();
        params.put("productId",productId);
        String response = HttpClientUtils.httpGetRequest(url,params);
        return JSONObject.parseObject(response, ProductInfoBO.class);
    }

    /**
     * 在 GetProductInfoCommand 中，重写 getCacheKey() 方法，这样的话，每一次请求的结果，都会放在 Hystrix 请求上下文中。
     * 下一次同一个 productId 的数据请求，直接取缓存，无须再调用 run() 方法。
     * @return
     */
    @Override
    protected String getCacheKey() {

        return "product_info_" + productId;
    }

    /**
     * 将某个商品id的缓存清空
     *
     * 用于我们开发手动删除缓存
     * @param productId 商品id
     */
    public static void flushCache(Long productId) {
        HystrixRequestCache.getInstance(KEY,
                HystrixConcurrencyStrategyDefault.getInstance()).clear("product_info_" + productId);
    }
}
