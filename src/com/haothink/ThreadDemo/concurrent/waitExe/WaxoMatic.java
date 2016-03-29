package com.haothink.ThreadDemo.concurrent.waitExe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaxoMatic {

	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

class Car{
	private boolean waxon = false;

	public synchronized void waxed(){
		waxon = true;
		notifyAll();
	}

	public synchronized void buffed(){
		waxon = false;
		notifyAll();
	}

	public synchronized void waitForWaxing() throws InterruptedException{
		while(waxon == false)
			wait();
	}

	public synchronized void waitForBuffing() throws InterruptedException{
		while(waxon == true)
			wait();
	}

}

class WaxOn implements Runnable{
	private Car car;

	public WaxOn(Car c){
		car = c;
	}
	@Override
	public void run() {

		try{
			while(!Thread.interrupted()){
				
				System.out.println("wax on ");
				TimeUnit.MILLISECONDS.sleep(200);
				car.waxed();
				car.waitForBuffing();
			}

		}catch(Exception e){
			System.out.println("Exiting via interrupt");
		}
		System.out.println("ending wax on task");
	}

}

class WaxOff implements Runnable{
	
	private Car car;
	public WaxOff(Car c){
		car = c;
	}
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				car.waitForWaxing();
				System.out.println("wax off");
				TimeUnit.MILLISECONDS.sleep(200);
				car.buffed();
			}
		}catch(Exception e){
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending wax off task");
	}
	
}
