package com.haothink.designpattern.proxy.jdkproxyprinciple;

import com.haothink.designpattern.proxy.Moveable;

public class CarTimeProxy implements Moveable{
	
	Moveable movie;
	
	
	public CarTimeProxy() {
		super();
		
	}
	
	public CarTimeProxy(Moveable movie) {
		super();
		this.movie = movie;
	}


	@Override
	public void start() {
		long start = System.currentTimeMillis();
		movie.start();
		long end = System.currentTimeMillis();
		
		System.out.println("time"+(end-start));
		
	}

}
