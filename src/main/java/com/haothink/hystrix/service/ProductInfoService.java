package com.haothink.hystrix.service;

import com.haothink.hystrix.BO.ProductInfoBO;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Observer;

/**
 * @author wanghao
 * @date 2019年06月13日 20:47
 * description:
 */
public class ProductInfoService {


    public String getProductInfo(Long productId) {
        HystrixCommand<ProductInfoBO> getProductInfoCommand = new GetProductInfoCommand(productId);

        // 通过command执行，获取最新商品数据
        ProductInfoBO productInfo = getProductInfoCommand.execute();
        System.out.println(productInfo);
        return "success";
    }

    public String getProductInfos(String productIds) {

        String[] productIdArray = productIds.split(",");
        HystrixObservableCommand<ProductInfoBO> getProductInfosCommand = new GetProductInfosCommand(productIdArray);
        Observable<ProductInfoBO> observable = getProductInfosCommand.observe();
        observable.subscribe(new Observer<ProductInfoBO>() {
            @Override
            public void onCompleted() {
                System.out.println("获取完了所有的商品数据");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            /**
             * 获取完一条数据，就回调一次这个方法
             * @param productInfo
             */
            @Override
            public void onNext(ProductInfoBO productInfo) {
                System.out.println(productInfo);
            }
        });

        return "success";
    }
}
