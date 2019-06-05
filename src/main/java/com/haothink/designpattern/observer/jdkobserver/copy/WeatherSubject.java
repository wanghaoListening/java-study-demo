package com.haothink.designpattern.observer.jdkobserver.copy;

import java.util.Observable;

public class WeatherSubject extends Observable {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		this.setChanged();
		//this.notifyObservers(content);
		this.notifyObservers();
	}		
}
