package com.haothink.rpc.service;

import com.haothink.rpc.service.HelloService;

/**
 * @author wanghao
 * @date 2019年08月29日 12:48
 * description:
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

}