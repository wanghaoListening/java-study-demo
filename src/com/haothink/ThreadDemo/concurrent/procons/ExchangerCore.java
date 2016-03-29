package com.haothink.ThreadDemo.concurrent.procons;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Exchanger实现生产者消费者模型，生产者与消费者约定好在某一地点交换货物，
 * 当某一者先到达时，那么会阻塞线程，当两者都到达时才交换货物，双方获得彼此的、
 * 数据。
 * */
public class ExchangerCore {

	public static void main(String[] args) {
		final Exchanger<String> exchanger = new Exchanger<String>();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExProducer(exchanger));
		exec.execute(new ExConsumer(exchanger));
	}
}

class ExProducer implements Runnable{
	private final Exchanger<String> exchanger;
	private static int count;
	public ExProducer(Exchanger<String> exchanger){
		this.exchanger = exchanger;
	}

	@Override
	public void run() {

		for(int i=count; i<=10; i++)
			try {
				String receive = exchanger.exchange((++count)+"");
				System.out.println(receive);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
	}
}



class ExConsumer implements Runnable{
	private final Exchanger<String> exchanger;
	public ExConsumer(Exchanger<String> exchanger){
		this.exchanger = exchanger;
	}
	@Override
	public void run() {

		while(true){
			String data = "receive the goods";
			try {
				String str = exchanger.exchange(data);
				System.out.println("consume the goods "+str);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

}
