package com.haothink.hystrix.service;


import com.alibaba.fastjson.JSONObject;
import com.haothink.hystrix.BO.ProductInfoBO;
import com.haothink.utils.HttpClientUtils;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.HashMap;
import java.util.Map;


/**
 * @author wanghao
 * @date 2019年06月13日 20:29
 * description:
 */
public class GetProductInfoCommand extends HystrixCommand<ProductInfoBO> {


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
}
