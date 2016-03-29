package com.haothink.ThreadDemo.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 此类是对AtomicityTest的改进
 * 原子类
 * java se5引入了诸如AtomicInter，AtomicLong，AtomicReference
 * Atomic被设计出来构建java.util.concurrent的类，因此只有在特殊的情况
 * 下才在自己的代码中使用它们，即使使用了也不确保不存在其他可能出现的问题，通常依赖
 * 于锁更加安全一些，synchronized，要么是显示的Lock对象。 
 * AtomicBoolean
 * AtomicInteger
 * AtomicIntegerArray
 * AtomicIntegerFieldUpdater操作对象里的整数
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
				while(true)
				in.increment();
				
			}
			
		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true)
				in.output();
				
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


