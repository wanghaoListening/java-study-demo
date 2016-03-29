package com.haothink.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 目标对象，它知道观察它的观察着，并提供注册添加和删除观察着的接口
 * @author wanghao
 * 
 * */
public class Subject {
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
	 * 提醒所有的观察着
	 * */
	protected void notifyObs(){
		for(Observer ob : obs){
			ob.update(this);
		}
	}
}
