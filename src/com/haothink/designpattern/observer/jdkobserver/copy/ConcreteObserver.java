package com.haothink.designpattern.observer.jdkobserver.copy;

import java.util.Observable;
import java.util.Observer;
/**
 * 观察者与被观察者之间是属于轻度的关连关系，并且是抽象耦合的。这样对
 * 两者来讲都比较容易扩展。
 * 缺点：由于是链式触发，当观察者比较多时时候，性能问题让人担忧
 * */
public class ConcreteObserver implements Observer{
	private String name;
	@Override
	public void update(Observable o, Object arg) {		
		//System.out.println(name+"推送的"+arg);
		System.out.println(name+"拉取得"+((WeatherSubject)o).getContent());		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
