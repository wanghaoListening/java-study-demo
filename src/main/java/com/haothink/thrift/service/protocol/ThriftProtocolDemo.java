package com.haothink.thrift.service.protocol;

import com.haothink.thrift.service.Pair;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wanghao on 2019-11-18
 * mail:wanghaotime@qq.com
 **/
public class ThriftProtocolDemo {

    public static void main(String[] args) throws IOException, TException {
        writeData();
        readData();

    }

    private static void writeData() throws IOException,TException{
        Pair pair = new Pair();
        pair.setKey("key1").setValue("value1");
        FileOutputStream fos = new FileOutputStream(new File("pair.txt"));
        pair.write(new TBinaryProtocol(new TIOStreamTransport(fos)));
        fos.close();
    }

    private static void readData() throws TException,IOException {
        Pair pair = new Pair();
        FileInputStream fis = new FileInputStream(new File("pair.txt"));
        pair.read(new TBinaryProtocol(new TIOStreamTransport(fis)));
        System.out.println("key => " + pair.getKey());
        System.out.println("value => " + pair.getValue());
        fis.close();
    }


}
