package com.haothink.ThreadDemo.concurrent.countdownlatch;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
 * 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 
 * 很有用。因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
 * */
public class CyclicBarrierTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final  CyclicBarrier cb = new CyclicBarrier(3);//创建CyclicBarrier对象并设置3个公共屏障点
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
				public void run(){
					try {
						Thread.sleep((long)(Math.random()*10000));    
						System.out.println("线程" + Thread.currentThread().getName() + 
								"即将到达集合地点1，当前已有" + (cb.getNumberWaiting()+1) + "个已经到达，正在等候");                       
						cb.await();//到此如果没有达到公共屏障点，则该线程处于等待状态，如果达到公共屏障点则所有处于等待的线程都继续往下运行
						 

						Thread.sleep((long)(Math.random()*10000));    
						System.out.println("线程" + Thread.currentThread().getName() + 								"即将到达集合地点2，当前已有" + (cb.getNumberWaiting()+1)+ "个已经到达，正在等候");                        
						cb.await();    
						Thread.sleep((long)(Math.random()*10000));    
						System.out.println("线程" + Thread.currentThread().getName() + 
								"即将到达集合地点3，当前已有" + (cb.getNumberWaiting()+1) + "个已经到达，正在等候");                        
						cb.await();                        
					} catch (Exception e) {
						e.printStackTrace();
					}                
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}