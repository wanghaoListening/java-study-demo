package com.haothink.designpattern.proxy.jdkproxyprinciple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {
	
	private Object target;
	
	
	public TimeHandler(Object target) {
		super();
		this.target = target;
	}


	@Override
	public void invoke(Object o, Method m) {
		 
		try {
			long starttime = System.currentTimeMillis();
			System.out.println("汽车开动了........");
			m.invoke(target);
			long endtime = System.currentTimeMillis();
			System.out.println("time"+(endtime-starttime)+endtime);
			
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			
			e.printStackTrace();
		}
	}

}
