package com.haothink.rpc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author wanghao
 * @date 2019年08月29日 13:30
 * description: rpc框架v2版本，使用线程池来处理请求线程
 */
public class RpcFrameworkV2 {


    private static int poolSize = Runtime.getRuntime().availableProcessors()+2;

    private static final ExecutorService executorService =
            new ThreadPoolExecutor(poolSize, poolSize,
            10, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),new ThreadFactoryBuilder()
            .setNameFormat("MDC-POOL-%d").build());




    public static void export(final Object service, int port) throws Exception {
        if (service == null) {
            throw new IllegalArgumentException("service instance == null");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        System.out.println("Export service " + service.getClass().getName() + " on port " + port);
        ServerSocket server = new ServerSocket(port);
        for(;;) {
            try {
                final Socket socket = server.accept();
                executorService.submit(new ServerTask(socket,service,port));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class ServerTask implements Runnable{

        private Socket socket;

        private Object service;

        private int port;

        public ServerTask(Socket socket,final Object service, int port) {
            this.socket = socket;
            this.service = service;
            this.port = port;
        }


        @Override
        public void run() {
            try {
                try {
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    try {
                        String methodName = input.readUTF();
                        Class<?>[] parameterTypes = (Class<?>[])input.readObject();
                        Object[] arguments = (Object[])input.readObject();
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                        try {
                            Method method = service.getClass().getMethod(methodName, parameterTypes);
                            Object result = method.invoke(service, arguments);
                            output.writeObject(result);
                        } catch (Throwable t) {
                            output.writeObject(t);
                        } finally {
                            output.close();
                        }
                    } finally {
                        input.close();
                    }
                } finally {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


