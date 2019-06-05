package com.haothink.thread.concurrent.procons;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Exchangerʵ��������������ģ�ͣ���������������Լ������ĳһ�ص㽻�����
 * ��ĳһ���ȵ���ʱ����ô�������̣߳������߶�����ʱ�Ž������˫����ñ˴˵ġ�
 * ���ݡ�
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
