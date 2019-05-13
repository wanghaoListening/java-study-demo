package com.haothink.thread.concurrent.interrupt;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/**
 * @author wanghao
 * nio���ṩ���ṩ�˸������Ի���I/O�жϡ���������nioͨ�����Զ�����Ӧ�ж�
 * */
@SuppressWarnings({ "unused", "resource" })
public class NIOInterruption {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		InetSocketAddress isa = new InetSocketAddress("localhost",8080);
		SocketChannel sc1 = SocketChannel.open(isa);
		SocketChannel sc2 = SocketChannel.open(isa);
		Future<?> f = 
		exec.submit(new NIOBlocked(sc1));
		exec.submit(new NIOBlocked(sc2));
		exec.shutdown();
		f.cancel(true);
		TimeUnit.SECONDS.sleep(1);
		sc2.close();

	}
}
@SuppressWarnings("unused")
class NIOBlocked implements Runnable{
	
	
	private SocketChannel sc;
	public NIOBlocked(SocketChannel sc){
		this.sc = sc;
	}
	@Override
	public void run() {
		try{
		   System.out.println("Waiting for read()"+this);
		}catch(Exception e){
			throw new RuntimeException();
		}
		System.out.println("Exiting NIOBlocked.run()"+this);
	}
	
}
