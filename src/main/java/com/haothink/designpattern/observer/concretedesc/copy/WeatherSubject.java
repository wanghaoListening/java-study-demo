package com.haothink.designpattern.observer.concretedesc.copy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 * 
 * */
public class WeatherSubject {
	List<Observer> obs = new ArrayList<Observer>();

	public void attach(Observer observer){
		obs.add(observer);
	}
	/**
	 * @param observer
	 * */
	public void delete(Observer observer){
		obs.remove(observer);
	}
	/**
	 * */
	protected void notifyObs(){
		System.out.println(obs.size());
		for(Observer ob : obs){
			ob.update(this);
		}
	}
}
