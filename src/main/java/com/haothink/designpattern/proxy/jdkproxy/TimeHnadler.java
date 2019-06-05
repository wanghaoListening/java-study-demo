package com.haothink.designpattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.haothink.designpattern.proxy.Moveable;



public class TimeHnadler implements InvocationHandler {

	private Moveable moveable=null;
	public TimeHnadler(Moveable moveable){
		this.moveable = moveable;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("start");
		method.invoke(moveable);
		System.out.println("end");
		return null;
	}

}
