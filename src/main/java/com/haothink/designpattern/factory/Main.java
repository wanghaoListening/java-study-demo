package com.haothink.designpattern.factory;

import com.haothink.designpattern.factory.spring.ApplicationContext;
import com.haothink.designpattern.factory.spring.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Object obj = ac.getBean("car");
		Car car = (Car)obj;
		car.run();
		
	}
}
