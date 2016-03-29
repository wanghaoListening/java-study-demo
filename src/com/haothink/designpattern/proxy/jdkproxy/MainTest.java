package com.haothink.designpattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.haothink.designpattern.proxy.Moveable;






public class MainTest {
	
	@Test
	public void demo(){
		Plane plane = new Plane();
		InvocationHandler h = new TimeHnadler(plane);
		Moveable m = (Moveable) Proxy.newProxyInstance(this.getClass().getClassLoader(), Plane.class.getInterfaces(), h);
		m.start();
		
	}
}
