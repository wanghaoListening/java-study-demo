package com.haothink.rpc.consumer;

import com.haothink.rpc.RpcFrameworkV1;
import com.haothink.rpc.service.HelloService;

/**
 * @author wanghao
 * @date 2019年08月29日 12:52
 * description:
 */
public class RpcConsumer {

    public static void main(String[] args) throws Exception {
        HelloService service = RpcFrameworkV1.refer(HelloService.class, "127.0.0.1", 1234);
        for (int i = 0; i < 10; i ++) {
            String hello = service.hello("World" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
