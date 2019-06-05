package com.haothink.designpattern.observer.concretedesc.copy;

public class Client {
	public static void main(String[] args) {
	
		ConcreteWeatherSubject ws = new ConcreteWeatherSubject();
	
		ConcreteObserver ob = new ConcreteObserver();
		ob.setName("name");
		ob.setContent("content");
		ConcreteObserver ob2 = new ConcreteObserver();
		ob2.setName("name2");
		ob2.setContent("content2");
		
		ws.attach(ob);
		ws.attach(ob2);
		ws.setSubjectState("subject");
																																																																																																																																																																																																																																																																																																								
	}
}
