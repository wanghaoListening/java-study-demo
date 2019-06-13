package com.haothink.hystrix.service;

import com.alibaba.fastjson.JSONObject;
import com.haothink.hystrix.BO.ProductInfoBO;
import com.haothink.utils.HttpClientUtils;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghao
 * @date 2019年06月13日 20:49
 * description:
 */
public class GetProductInfosCommand extends HystrixObservableCommand<ProductInfoBO> {

    private String[] productIds;

    public GetProductInfosCommand(String[] productIds) {
        // 还是绑定在同一个线程池
        super(HystrixCommandGroupKey.Factory.asKey("GetProductInfoGroup"));
        this.productIds = productIds;
    }

    @Override
    protected Observable<ProductInfoBO> construct() {

        return Observable.create((Observable.OnSubscribe<ProductInfoBO>) subscriber -> {

            for (String productId : productIds) {
                // 批量获取商品数据
                String url = "http://localhost:8081/getProductInfo";
                Map<String,Object> params = new HashMap<>();
                params.put("productId",productId);
                String response = null;
                try {
                    response = HttpClientUtils.httpGetRequest(url,params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ProductInfoBO productInfo = JSONObject.parseObject(response, ProductInfoBO.class);
                subscriber.onNext(productInfo);
            }
            subscriber.onCompleted();

        }).subscribeOn(Schedulers.io());
    }
}
