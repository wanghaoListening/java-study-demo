package com.haothink.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wanghao
 * @date 2019年07月11日 15:00
 * description:
 */
public class LocalServer {


    public static void main(String[] args) throws IOException {


        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        serverSocket.bind(address);

        while (true){

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()){

                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()){
                    ServerSocketChannel sSocketChannel = (ServerSocketChannel)key.channel();
                    SocketChannel socketChannel = sSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)key.channel();
                    String data = readDateFromChannel(socketChannel);
                    System.out.println(data);
                    socketChannel.close();
                }

                keyIterator.remove();
            }
        }

    }





    private static String readDateFromChannel(SocketChannel socketChannel) throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        StringBuilder stringBuilder = new StringBuilder();

        while (true){

            int nByte = socketChannel.read(byteBuffer);
            if(nByte <= -1){
                break;
            }
            //The limit is set to the current position
            byteBuffer.flip();
            int limit = byteBuffer.limit();

            char[] chs = new char[limit];

            for(int i=0;i<limit;i++){
                char ch = (char)byteBuffer.get(i);
                chs[i] = ch;
            }

            stringBuilder.append(chs);
            //清空buffer
            byteBuffer.clear();
        }

        return stringBuilder.toString();
    }
}
