package com.haothink.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wanghao
 * 
 * */
public class Subject {
	List<Observer> obs = new ArrayList<>();
	
	public void attach(Observer observer){
		obs.add(observer);
	}
	/**
	 * @param observer
	 * */
	public void delete(Observer observer){
		obs.remove(observer);
	}

	protected void notifyObs(){
		for(Observer ob : obs){
			ob.update(this);
		}
	}
}
