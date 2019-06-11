package com.haothink.rpc.register;

import com.haothink.rpc.provider.HaoProvider;

import java.util.List;

/**
 * @author wanghao
 * @date 2019年06月11日 11:21
 * description: 服务发布者
 */
public interface Publisher {

    public void registerService(List<HaoProvider> providerList);
}
