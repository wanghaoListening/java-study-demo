package com.haothink.thread.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicBoolean
 * AtomicInteger
 * AtomicIntegerArray
 * AtomicIntegerFieldUpdater
 * AtomicLong
 * AtomicLongArray
 * AtomicLongArrayFieldUpdater
 * AtomicMarkableReference
 * AtomicReference
 * AtomicReferenceArray
 * AtomicReferenceFieldUpdater
 * AtomicStampedReference
 * */
public class AtomicIntegerTest {
	static IntegerIncrement in =new IntegerIncrement();
	public static void main(String[] args) {
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true) {
					in.increment();
				}
				
			}
			
		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true) {
                    in.output();
                }
				
			}
			
		}).start();
	}
	
	

}

class IntegerIncrement{
	private AtomicInteger ai = new AtomicInteger(0);
	
	public void increment(){
		ai.addAndGet(2);
	}
	
	public void output(){
		System.out.println(ai.get());
	}
}


