package com.haothink.designpattern.observer.jdkobserver.copy;

public class Client {
	
	public static void main(String[] args) {
		WeatherSubject ws = new WeatherSubject();
		
		ConcreteObserver co = new ConcreteObserver();
		co.setName("al");
		ws.addObserver(co);
		
		ws.setContent("hi old brother");
	}
}
