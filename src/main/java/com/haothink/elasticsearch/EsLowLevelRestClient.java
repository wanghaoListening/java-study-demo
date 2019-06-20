package com.haothink.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;


import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author wanghao
 * @date 2019年06月20日 15:08
 * description:
 */
public class EsLowLevelRestClient {


    private static RestClient restClient;

    static {

        restClient = RestClient.builder(
                new HttpHost("10.143.130.196", 9200, "http")).build();
    }

    public static String catApi() throws IOException {
        Request request = new Request(
                "GET",
                "/_cat");
        Response response = restClient.performRequest(request);
        return EntityUtils.toString(response.getEntity());
    }


    public static String createObject(String endpoint, Map<String,String> params,Object obj) throws IOException {

        return performEsRequest("PUT",endpoint,params,obj);
    }

    public static String postObject(String endpoint, Map<String,String> params,Object obj) throws IOException {

        return performEsRequest("POST",endpoint,params,obj);
    }

    public static String getObject(String endpoint) throws IOException {

        return performEsRequest("GET",endpoint,null,null);
    }

    private static String performEsRequest(String method,String endpoint, Map<String,String> params,Object obj) throws IOException {
        Request request = new Request(method,endpoint);
        if(Objects.nonNull(obj)) {
            HttpEntity entity = new NStringEntity(JSONObject.toJSONString(obj),ContentType.APPLICATION_JSON);
            request.setEntity(entity);
        }
        if(Objects.nonNull(params) && !params.isEmpty()){
            params.forEach(request::addParameter);
        }
        Response response = restClient.performRequest(request);

        return EntityUtils.toString(response.getEntity());
    }

    public void close(){

        if(Objects.nonNull(restClient)){

            try {
                restClient.close();
            } catch (IOException e) {
                throw new RuntimeException("close EsLowLevelRestClient fail !");
            }
        }
    }
}
