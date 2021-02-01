package com.haothink.gateway;


/**
 * Created by wanghao on 2020-07-18
 * mail:hiwanghao
 **/
public interface ResponseDataConverter {

    EleAccountInfo convert(String responseData);
}
