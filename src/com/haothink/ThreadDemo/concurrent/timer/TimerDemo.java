package com.haothink.ThreadDemo.concurrent.timer;

import java.util.Timer;
import java.util.TimerTask;
/**
 * 定时器2秒和10秒定时爆破
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
