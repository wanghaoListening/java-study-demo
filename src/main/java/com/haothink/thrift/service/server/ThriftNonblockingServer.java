package com.haothink.thrift.service.server;

import com.haothink.thrift.service.HelloWorldService;
import com.haothink.thrift.service.impl.HelloWorldServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by wanghao on 2019-11-16
 * mail:wanghaotime@qq.com
 **/
public class ThriftNonblockingServer {


    public static void main(String[] args) throws TTransportException {

        TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());

        TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(8888);

        TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(tnbSocketTransport);
        tnbArgs.processor(tprocessor);
        tnbArgs.transportFactory(new TFramedTransport.Factory());
        tnbArgs.protocolFactory(new TCompactProtocol.Factory());

        //使用非阻塞式IO服务端和客户端需要指定TFramedTransport数据传输的方式
        TServer server = new TNonblockingServer(tnbArgs);
        System.out.println("Running Non-blocking Server");
        server.serve();

    }
}
