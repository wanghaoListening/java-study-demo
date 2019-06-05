package com.haothink.designpattern.proxy;

public class MoveProxy implements Moveable {

	Moveable mv;
	
	
	public MoveProxy(Moveable mv) {
		super();
		this.mv = mv;
	}


	@Override
	public void start() {
		System.out.println("------------------");
		mv.start();
		System.out.println("------------------");
	}

}
