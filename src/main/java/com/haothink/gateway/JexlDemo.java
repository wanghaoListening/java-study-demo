package com.haothink.gateway;

import com.alibaba.fastjson.JSONObject;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghao on 2020-07-19
 * mail:hiwanghao@didiglobal.com
 **/
public class JexlDemo {


    private Template jsonBodyTempl;

    public static void main(String[] args) {

        //jexl freemarker
        JexlDemo jexlDemo = new JexlDemo();
        jexlDemo.testJexl();
    }




    public void testJexl(){


        String json = "{\n" +
                "   \"https\":true,\n" +
                "   \"method\":\"post\",\n" +
                "   \"param\": [\"channelId=$.account\",\"signature=$.md5($.password)\"],\n" +
                "   \"jsonParam\": {\n" +
                "   \t\t\"bizData\":\"\",\n" +
                "   \t\t\"channelId\":\"${p.channelId}\",\n" +
                "   \t\t\"interfaceId\":\"${p.interfaceId}\",\n" +
                "   \t\t\"requestId\":\"${p.requestId}\",\n" +
                "   \t\t\"signature\":\"${$.md5('requestId=${p.applicationNo}&channelId=${p.channelId}&interfaceId=${p.serviceId}&timeStamp=${timestampSec}&bizData=${p.requestId}')}\",\n" +
                "   \t\t\"timeStamp\":\"${timestampSec}\"\n" +
                "   },\n" +
                "   \"contentType\": \"\",\n" +
                "   \"headerParam\": \"\"\n" +
                " }";


        init(json);

        Map<String, Object> ctxMap = new HashMap<String, Object>();

        ctxMap.put("channelId","DiDi");
        ctxMap.put("interfaceId","S004");
        ctxMap.put("requestId","S85f3f6d42b594fa9a935420200717144231");
        ctxMap.put("applicationNo","meto");
        ctxMap.put("serviceId","444444");

        String requestBody = evalJsonBody(ctxMap);

        System.out.println(requestBody);

    }


    private void init(String jsonStr){

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        if (jsonObject.containsKey("jsonParam")) {
            //如果是post json 的方式，则参数规则字段是一个json 字符串
            final Configuration cfg = new Configuration();
            cfg.setNumberFormat("#");
            try {
                jsonBodyTempl = new Template("jsonBodyTempl", new StringReader(jsonObject.getString("jsonParam")), cfg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String evalJsonBody(Map<String, Object> ctxMap) {
        final Map<String, Object> data = new HashMap<String, Object>(3);
        data.put("p", ctxMap);
        data.put("timestampSec", nowUnixTime());
        data.put("currentTimeMillis", nowTime());
        BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        TemplateHashModel staticMethods = null;
        try {
            staticMethods = (TemplateHashModel) staticModels.get(JexlDemo.class.getCanonicalName());
        } catch (TemplateModelException e) {
            throw new RuntimeException(e);
        }
        data.put("$", staticMethods);

        final Writer out = new StringWriter();
        try {
            this.jsonBodyTempl.process(data, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return out.toString();
    }

    public static long nowTime() {
        return System.currentTimeMillis();
    }

    public static long nowUnixTime() {
        return System.currentTimeMillis() / 1000;
    }

    // below is the functions using in expression
    public static String md5(final String source) {
        return DigestUtils.md5Hex(source);
    }
}
