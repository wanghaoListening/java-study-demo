package com.haothink.thrift.service.client;

import com.haothink.thrift.service.HelloWorldService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;


/**
 * Created by wanghao on 2019-11-15
 * mail:wanghaotime@qq.com
 **/
public class SimpleClient {

    public static void main(String[] args) {


        TTransport transport = null;
        try {
            transport = new TSocket("127.0.0.1", 8888, 3000);
            TProtocol protocol = new TBinaryProtocol(transport);
            HelloWorldService.Client client = new HelloWorldService.Client(protocol);
            transport.open();

            String result = client.say("ThreadPoolServer");
            System.out.println("Result =: " + result);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }

    }
}
