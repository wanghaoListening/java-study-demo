package com.haothink.gateway;

/**
 * Created by wanghao on 2020-07-18
 * mail:hiwanghao@didiglobal.com
 *
 * 用于滴滴内请求参数到机构侧参数的转化
 **/
public interface RequestDataConverter {


    String convert(EleAccountCreateReq eleAccountCreateReq);
}
