package com.haothink.designpattern.observer.concretedesc;

import java.util.ArrayList;
import java.util.List;

/**
 * 目标对象，它知道观察它的观察着，并提供注册添加和删除观察着的接口
 * @author wanghao
 * 
 * */
public class WeatherSubject {
	List<Observer> obs = new ArrayList<Observer>();
	//把订阅天气的人添加到集合中
	public void attach(Observer observer){
		obs.add(observer);
	}
	/**
	 * @param observer
	 * 删除订阅天气的人
	 * */
	public void delete(Observer observer){
		obs.remove(observer);
	}
	/**
	 * 提醒所有的订阅天气的人
	 * */
	protected void notifyObs(){
		System.out.println(obs.size());
		for(Observer ob : obs){
			ob.update(this);
		}
	}
}
