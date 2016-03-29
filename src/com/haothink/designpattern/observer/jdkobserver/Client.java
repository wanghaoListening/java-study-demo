package com.haothink.designpattern.observer.jdkobserver;

public class Client {
	
	public static void main(String[] args) {
		WeatherSubject ws = new WeatherSubject();
		
		ConcreteObserver co = new ConcreteObserver();
		co.setName("al");
		ws.addObserver(co);
		
		ws.setContent("今天天气好晴朗");
	}
}
