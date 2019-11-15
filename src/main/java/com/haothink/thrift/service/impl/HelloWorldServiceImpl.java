package com.haothink.thrift.service.impl;

import org.apache.thrift.TException;

/**
 * Created by wanghao on 2019-11-15
 * mail:wanghaotime@qq.com
 **/
public class HelloWorldServiceImpl implements HelloWorldService.Iface {

    public String say(String username) throws TException {

        return "Hello " + username;
    }
}
