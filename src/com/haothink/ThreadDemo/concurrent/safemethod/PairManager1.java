package com.haothink.ThreadDemo.concurrent.safemethod;

public class PairManager1 extends PairManager {

	@Override
	public synchronized void increment() {
		
		p.incrementX();
		p.incrementY();
		store(getPair());
	}

}
