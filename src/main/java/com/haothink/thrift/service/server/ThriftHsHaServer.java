package com.haothink.thrift.service.server;

import com.haothink.thrift.service.HelloWorldService;
import com.haothink.thrift.service.impl.HelloWorldServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by wanghao on 2019-11-16
 * mail:wanghaotime@qq.com
 *
 * THsHaServer是半同步半异步(Half-Sync/Half-Async)的处理模式，Half-Aysnc用于IO事件处理(Accept/Read/Write)，
 * Half-Sync用于业务handler对rpc的同步处理上。
 **/
public class ThriftHsHaServer {

    public static void main(String[] args) throws TTransportException {

        TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(8888);
        TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
        // 半同步半异步
        THsHaServer.Args thhsArgs = new THsHaServer.Args(tnbSocketTransport);
        thhsArgs.processor(tprocessor);
        thhsArgs.transportFactory(new TFramedTransport.Factory());
        thhsArgs.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new THsHaServer(thhsArgs);
        System.out.println("Running HsHa Server");
        server.serve();


    }
}
