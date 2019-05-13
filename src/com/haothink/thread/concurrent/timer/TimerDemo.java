package com.haothink.thread.concurrent.timer;

import java.util.Timer;
import java.util.TimerTask;
/**
 * ��ʱ��2���10�붨ʱ����
 * */
public class TimerDemo {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new MyTask(), 2000);
	}
}

class MyTask extends TimerTask{
	private static int count;
	@Override
	public void run() {
		System.out.println("bombing");
		Timer timer = new Timer();
		timer.schedule(new MyTask(), 2000+8000*((count++)%2));

	}

}
