package com.haothink.designpattern.observer.jdkobserver.copy;

import java.util.Observable;
import java.util.Observer;
/**
 *
 *
 * */
public class ConcreteObserver implements Observer{
	private String name;
	@Override
	public void update(Observable o, Object arg) {

		System.out.println(name+"----"+((WeatherSubject)o).getContent());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
