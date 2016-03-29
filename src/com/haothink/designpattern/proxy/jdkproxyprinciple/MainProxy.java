package com.haothink.designpattern.proxy.jdkproxyprinciple;

import com.haothink.designpattern.proxy.Car;
import com.haothink.designpattern.proxy.Moveable;

public class MainProxy {
	public static void main(String[] args) throws Exception {
		Car car = new Car();
		InvocationHandler h = new TimeHandler(car);
		Moveable m = (Moveable)Proxy.newProxyInstance(Moveable.class,h);
		System.out.println(m.getClass().getName());
		m.start();
		
	}
	
	
}
