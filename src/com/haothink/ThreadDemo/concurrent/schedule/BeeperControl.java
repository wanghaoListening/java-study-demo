package com.haothink.ThreadDemo.concurrent.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.*;
/**
 *  schedule 方法都接受相对 延迟和周期作为参数，而不是绝对的时间或日期。将以 Date 
 *  所表示的绝对时间转换成要求的形式很容易。例如，要安排在某个以后的 Date 运行，
 *  可以使用：schedule(task, date.getTime() - System.currentTimeMillis(), 
 *  TimeUnit.MILLISECONDS)。
 * */
public class BeeperControl {
	private final ScheduledExecutorService scheduler = 
			Executors.newScheduledThreadPool(1);

	public void beepForAnHour() {
		final Runnable beeper = new Runnable() {
			public void run() { System.out.println("beep"); }
		};
		
		final ScheduledFuture<?> beeperHandle = 
				//以固定的频率启动定时器
				scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() { beeperHandle.cancel(true); }
		}, 60 * 60, SECONDS);
		
		scheduler.schedule(new Runnable(){

			@Override
			public void run() {
				System.out.println("延迟了");
				
			}
			
		}, 15, SECONDS);
	}

}
