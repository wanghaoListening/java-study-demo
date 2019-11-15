package com.haothink.thrift.service.server;

import com.haothink.thrift.service.impl.HelloWorldServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by wanghao on 2019-11-15
 * mail:wanghaotime@qq.com
 **/
public class SimpleServer {


    public static void main(String[] args) throws IOException, TTransportException {

        ServerSocket serverSocket = new ServerSocket(8888);
        TServerSocket serverTransport = new TServerSocket(serverSocket);
        HelloWorldService.Processor processor =
                new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());

        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();

        TSimpleServer.Args tArgs = new TSimpleServer.Args(serverTransport);
        tArgs.processor(processor);
        tArgs.protocolFactory(protocolFactory);


        // 简单的单线程服务模型 一般用于测试
        TServer tServer = new TSimpleServer(tArgs);
        System.out.println("Running Simple Server");
        tServer.serve();




    }
}
