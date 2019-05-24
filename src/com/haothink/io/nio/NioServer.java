package com.haothink.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wanghao
 * @date 2019年05月24日 16:53
 * description:
 */
public class NioServer {

    private static int clientCount = 0;
    private static AtomicInteger counter = new AtomicInteger(0);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {

        try {
            Selector selector = Selector.open();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            ssc.bind(new InetSocketAddress("localhost", 8080));
            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        ServerSocketChannel ssc1 = (ServerSocketChannel)key.channel();
                        SocketChannel sc = null;
                        while ((sc = ssc1.accept()) != null) {
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            InetSocketAddress rsa = (InetSocketAddress)sc.socket().getRemoteSocketAddress();
                            System.out.println(time() + "->" + rsa.getHostName() + ":" + rsa.getPort() + "->" + Thread.currentThread().getId() + ":" + (++clientCount));
                        }
                    } else if (key.isReadable()) {
                        //先将“读”从感兴趣操作移出，待把数据从通道中读完后，再把“读”添加到感兴趣操作中
                        //否则，该通道会一直被选出来
                        key.interestOps(key.interestOps() & (~ SelectionKey.OP_READ));
                        processWithNewThread((SocketChannel)key.channel(), key);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    private static void processWithNewThread(SocketChannel sc, SelectionKey key) {
        Runnable run = () -> {
            counter.incrementAndGet();
            try {
                String result = readBytes(sc);
                //把“读”加进去
                key.interestOps(key.interestOps() | SelectionKey.OP_READ);
                System.out.println(time() + "->" + result + "->" + Thread.currentThread().getId() + ":" + counter.get());
                sc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            counter.decrementAndGet();
        };
        new Thread(run).start();
    }



    private static String readBytes(SocketChannel sc) throws Exception {
        long start = 0;
        int total = 0;
        int count = 0;
        ByteBuffer bb = ByteBuffer.allocate(1024);
        //开始读数据的时间
        long begin = System.currentTimeMillis();
        while ((count = sc.read(bb)) > -1) {
            if (start < 1) {
                //第一次读到数据的时间
                start = System.currentTimeMillis();
            }
            total += count;
            bb.clear();
        }
        //读完数据的时间
        long end = System.currentTimeMillis();
        return "wait=" + (start - begin) + "ms,read=" + (end - start) + "ms,total=" + total + "bs";
    }

    private static String time() {
        return LocalDateTime.now().format(formatter);
    }



}
