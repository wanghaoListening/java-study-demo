package com.haothink.thrift.service.server;

import com.haothink.thrift.service.HelloWorldService;
import com.haothink.thrift.service.impl.HelloWorldServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by wanghao on 2019-11-16
 * mail:wanghaotime@qq.com
 **/
public class ThriftThreadPoolServer {


    public static void main(String[] args) throws IOException, TTransportException {

        ServerSocket serverSocket = new ServerSocket(8888);
        TServerSocket serverTransport = new TServerSocket(serverSocket);
        HelloWorldService.Processor<HelloWorldService.Iface> processor =
                new HelloWorldService.Processor<>(new HelloWorldServiceImpl());

        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
        TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(serverTransport);
        ttpsArgs.processor(processor);
        ttpsArgs.protocolFactory(protocolFactory);

        // 线程池服务模型 使用标准的阻塞式IO 预先创建一组线程处理请求
        TServer ttpsServer = new TThreadPoolServer(ttpsArgs);
        System.out.println("Running ThreadPool Server");
        ttpsServer.serve();

    }
}
