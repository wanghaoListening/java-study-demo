package com.haothink.designpattern.proxy.jdkproxy;

import com.haothink.designpattern.proxy.Moveable;

public class Plane implements Moveable{

	@Override
	public void start() {
		System.out.println("plane run ...");
		
	}

}
