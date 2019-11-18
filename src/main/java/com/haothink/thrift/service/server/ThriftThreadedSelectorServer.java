package com.haothink.thrift.service.server;

import com.haothink.thrift.service.HelloWorldService;
import com.haothink.thrift.service.impl.HelloWorldServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;


/**
 * Created by wanghao on 2019-11-16
 * mail:wanghaotime@qq.com
 **/
public class ThriftThreadedSelectorServer {


    public static void main(String[] args) throws TTransportException {


        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(8888);
        TProcessor processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
        // 多线程半同步半异步
        TThreadedSelectorServer.Args ttssArgs = new TThreadedSelectorServer.Args(serverSocket);
        ttssArgs.processor(processor);
        ttssArgs.protocolFactory(new TBinaryProtocol.Factory());

        // 使用非阻塞式IO时 服务端和客户端都需要指定数据传输方式为TFramedTransport
        ttssArgs.transportFactory(new TFramedTransport.Factory());

        // 多线程半同步半异步的服务模型
        TThreadedSelectorServer server = new TThreadedSelectorServer(ttssArgs);
        System.out.println("Running ThreadedSelector Server");
        server.serve();

    }
}
