package com.haothink.hystrix.service;

import com.haothink.hystrix.cache.BrandCache;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @author wanghao
 * @date 2019年06月15日 17:13
 * description: 获取品牌名称的command
 * FallbackIsolationSemaphoreMaxConcurrentRequests 用于设置 fallback 最大允许的并发请求量，默认值是 10，
 * 是通过 semaphore 信号量的机制去限流的。如果超出了这个最大值，那么直接 reject。
 */
public class GetBrandNameCommand extends HystrixCommand<String> {

    private Long brandId;


    public GetBrandNameCommand(Long brandId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BrandService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetBrandNameCommand"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        // 设置降级机制最大并发请求数
                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(15)));
        this.brandId = brandId;
    }

    @Override
    protected String run() throws Exception {
        // 这里正常的逻辑应该是去调用一个品牌服务的接口获取名称
        // 如果调用失败，报错了，那么就会去调用fallback降级机制

        // 这里我们直接模拟调用报错，抛出异常
        throw new Exception();
    }

    @Override
    protected String getFallback() {
        return BrandCache.getBrandName(brandId);
    }
}
