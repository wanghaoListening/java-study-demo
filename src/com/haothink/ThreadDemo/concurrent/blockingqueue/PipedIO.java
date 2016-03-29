package com.haothink.ThreadDemo.concurrent.blockingqueue;

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
 * Receiver 没有sleep()和wait()，当它调用read()时，如果没有更多的数据，管道将自动阻塞。
 * 注意：在shutdownNow被调用时，可以看到PipedReader与普通I/O之间最重要的差异-PipedReader
 * 是可中断的。如果你将in.read()调用修改为System.in.read()。那么interrupt()将不能打断
 * read()的调用。
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
