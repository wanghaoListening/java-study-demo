package com.haothink.designpattern.observer.jdkobserver.copy;

import java.util.Observable;

public class WeatherSubject extends Observable {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		//注意在通知观察者时此句话必不可少
		this.setChanged();
		//主动通知，推模型
		//this.notifyObservers(content);
		//主动通知，拉模型
		this.notifyObservers();
	}		
}
