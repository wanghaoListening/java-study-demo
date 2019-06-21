package com.haothink.elasticsearch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

/**
 * @author wanghao
 * @date 2019年06月20日 15:36
 * description:
 */
public class EsMainClient {


    public static void main(String[] args) throws IOException {
        lowLevelGetCat();
    }

    private static void lowLevelGetCat() throws IOException {
        RestClient restClient = RestClient.builder(
                new HttpHost("10.143.130.666", 9200, "http")).build();

        Request request = new Request(
                "GET",
                "/_cluster/health");
        HttpEntity entity = new NStringEntity("", ContentType.APPLICATION_JSON);
        request.setEntity(entity);
        Response response = restClient.performRequest(request);

        System.out.println(EntityUtils.toString(response.getEntity()));

        restClient.close();

    }


}
