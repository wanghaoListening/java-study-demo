package com.haothink.io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author wanghao
 * @date 2019年05月24日 17:34
 * description: 参考文章 ：https://mp.weixin.qq.com/s/v3zq7O3Ystl_QCpxTOUo4w
 */
public class AioServer {


    private static AtomicInteger counter = new AtomicInteger(0);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static int clientCount = 0;

    public static void main(String[] args) {

        try {

            AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open();
            assc.bind(new InetSocketAddress("localhost", 8080));
            assc.accept(null,new CompletionHandler<AsynchronousSocketChannel, Object>(){
                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {

                    assc.accept(null,this);
                    try {
                        InetSocketAddress rsa = (InetSocketAddress)result.getRemoteAddress();
                        System.out.println(time() + "->" + rsa.getHostName() + ":" + rsa.getPort() + "->" + Thread.currentThread().getId() + ":" + (++clientCount));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    readFromChannelAsync(result);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {

                }
            });

            //不让主线程退出
            synchronized (AioServer.class) {
                AioServer.class.wait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void readFromChannelAsync(AsynchronousSocketChannel asc){
        //会把数据读入到该buffer之后，再触发工作线程来执行回调
        ByteBuffer bb = ByteBuffer.allocate(1024*1024);
        long begin = System.currentTimeMillis();
        //非阻塞方法，其实就是注册了个回调，而且只能接受一次读取
        asc.read(bb,null, new CompletionHandler<Integer, Object>() {

            //从该连接上一共读到的字节数
            int total = 0;
            /**
             * @param count 表示本次读取到的字节数，-1表示数据已读完
             */
            @Override
            public void completed(Integer count, Object attachment) {
                counter.incrementAndGet();
                if (count > -1) {
                    total += count;
                }
                int size = bb.position();
                System.out.println(time() + "->count=" + count + ",total=" + total + "bs,buffer=" + size + "bs->" + Thread.currentThread().getId() + ":" + counter.get());
                //数据还没有读完
                if (count > -1) {
                    //再次注册回调，接受下一次读取
                    asc.read(bb, null, this);
                }else{
                    //数据已经读完
                    try {
                        asc.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                counter.decrementAndGet();
            }

            @Override
            public void failed(Throwable exc, Object attachment) {

            }
        });

        long end = System.currentTimeMillis();
        System.out.println(time() + "->exe read req,use=" + (end -begin) + "ms" + "->" + Thread.currentThread().getId());

    }




    private static String time() {
        return LocalDateTime.now().format(formatter);
    }
}
