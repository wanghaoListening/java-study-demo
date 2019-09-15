package com.haothink.rpc.provider;

import com.haothink.rpc.RpcFrameworkV1;
import com.haothink.rpc.RpcFrameworkV2;
import com.haothink.rpc.service.HelloService;
import com.haothink.rpc.service.HelloServiceImpl;

/**
 * @author wanghao
 * @date 2019年08月29日 12:49
 * description:
 */
public class RpcProvider {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFrameworkV2.export(service, 1234);
    }
}
