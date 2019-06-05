package com.haothink.designpattern.proxy.cglibproxy;

import com.haothink.designpattern.proxy.Car;

public class MainCg {
	
	public static void main(String[] args) {
		CglibHandler cg =new CglibHandler();
		Car car = (Car)cg.getProxy(Car.class);
		car.start();
	}
	
}
