package com.haothink.thread.concurrent.blockingqueue;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 	Read: A,
	Read: B,
	Read: C,
	Read: D,
	Read: E,
	Read: F,
	Read: G,
	Read: H,
	Read: I,
	Read: J,
	Read: K,
	Read: L,
	java.lang.InterruptedException: sleep interruptedsender sleep interrupted
	java.io.InterruptedIOExceptionReceiver read exception
 * 
 * Receiver û��sleep()��wait()����������read()ʱ�����û�и�������ݣ��ܵ����Զ�������
 * ע�⣺��shutdownNow������ʱ�����Կ���PipedReader����ͨI/O֮������Ҫ�Ĳ���-PipedReader
 * �ǿ��жϵġ�����㽫in.read()�����޸�ΪSystem.in.read()����ôinterrupt()�����ܴ��
 * read()�ĵ��á�
 * 
 * */
public class PipedIO {
	public static void main(String[] args) throws IOException, InterruptedException {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}
}

class Sender implements Runnable{
	private Random random = new Random(47);
	private PipedWriter pw = new PipedWriter();
	
	public PipedWriter getPipedWriter(){
		return pw;
	}
	@Override
	public void run() {
		try{
			while(true){
				for(char c='A';c <= 'Z';c++){
					pw.write(c);
					TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
				}
			}
		}catch(IOException e){
			System.out.println(e+"sender write exception");
		}catch(InterruptedException e){
			System.out.println(e+"sender sleep interrupted");
		}
		
	}
	
}

class Receiver implements Runnable{
	private PipedReader in;
	public Receiver(Sender sender)throws IOException{
		in = new PipedReader(sender.getPipedWriter());
	}
	@Override
	public void run() {
		
		try{
			while(true){
				System.out.println("Read: "+(char)in.read()+",");
			}
		}catch(IOException e){
			System.out.println(e+"Receiver read exception");
		}
	}
	
}
