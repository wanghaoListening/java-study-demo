package com.haothink.thread.concurrent.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.*;
/**
 *  schedule ������������� �ӳٺ�������Ϊ�����������Ǿ��Ե�ʱ������ڡ����� Date 
 *  ����ʾ�ľ���ʱ��ת����Ҫ�����ʽ�����ס����磬Ҫ������ĳ���Ժ�� Date ���У�
 *  ����ʹ�ã�schedule(task, date.getTime() - System.currentTimeMillis(), 
 *  TimeUnit.MILLISECONDS)��
 * */
public class BeeperControl {
	private final ScheduledExecutorService scheduler = 
			Executors.newScheduledThreadPool(1);

	public void beepForAnHour() {
		final Runnable beeper = new Runnable() {
			public void run() { System.out.println("beep"); }
		};
		
		final ScheduledFuture<?> beeperHandle = 
				//�Թ̶���Ƶ��������ʱ��
				scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() { beeperHandle.cancel(true); }
		}, 60 * 60, SECONDS);
		
		scheduler.schedule(new Runnable(){

			@Override
			public void run() {
				System.out.println("�ӳ���");
				
			}
			
		}, 15, SECONDS);
	}

}
