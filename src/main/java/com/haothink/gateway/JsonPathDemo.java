package com.haothink.gateway;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;

import java.util.Properties;

/**
 * Created by wanghao on 2020-07-17
 * mail:hiwanghao@didiglobal.com
 **/
public class JsonPathDemo {


    public static void main(String[] args) {


        Properties properties = new Properties();

        properties.put("userName","$.userName");
        properties.put("bankCardNo","$.bankCardNo");


        EleAccountCreateReq eleAccountCreateReq = new EleAccountCreateReq();
        eleAccountCreateReq.setUserName("helloword");
        eleAccountCreateReq.setBankCardNo("123456");


        Object obj = JSONObject.toJSON(eleAccountCreateReq);

        String invokeStatus = JsonPath.read(obj,properties.getProperty("userName"));
        System.out.println(invokeStatus);
    }



}
