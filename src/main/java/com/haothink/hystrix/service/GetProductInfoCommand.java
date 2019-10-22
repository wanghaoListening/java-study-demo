package com.haothink.hystrix.service;


import com.alibaba.fastjson.JSONObject;
import com.haothink.hystrix.BO.ProductInfoBO;
import com.haothink.utils.HttpClientUtils;
import com.netflix.hystrix.*;
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
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetProductInfoCommandGroup"))
                .andCommandKey(KEY)
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(8)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(8))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        // 是否允许断路器工作
                        .withCircuitBreakerEnabled(true)
                        // 滑动窗口中，最少有多少个请求，才可能触发断路
                        .withCircuitBreakerRequestVolumeThreshold(20)
                        // 异常比例达到多少，才触发断路，默认50%
                        .withCircuitBreakerErrorThresholdPercentage(40)
                        // 断路后多少时间内直接reject请求，之后进入half-open状态，默认5000ms
                        .withCircuitBreakerSleepWindowInMilliseconds(3000)
                        // 设置是否打开超时，默认是true
                        .withExecutionTimeoutEnabled(true)
                        // 设置超时时间，默认1000(ms)
                        .withExecutionTimeoutInMilliseconds(500)
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(30)));
        this.productId = productId;
    }

    @Override
    protected ProductInfoBO run() throws Exception {
        String url = "http://localhost:8081/getProductInfo";
        // 调用商品服务接口
        Map<String,String> params = new HashMap<>();
        params.put("productId",productId+"");
        String response = HttpClientUtils.postParameters(url,params);
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

    @Override
    protected ProductInfoBO getFallback() {
        //直接返回为null
        return null;
    }
}
