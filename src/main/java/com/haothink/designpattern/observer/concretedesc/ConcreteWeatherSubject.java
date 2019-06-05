package com.haothink.designpattern.observer.concretedesc;

public class ConcreteWeatherSubject extends WeatherSubject {
	
	private String weatherContent;

	public String getSubjectState() {
		return weatherContent;
	}
	/**
	 * 当目标者状态改变时提醒观察者
	 * 
	 * */
	public void setSubjectState(String weatherContent) {
		this.weatherContent = weatherContent;
		notifyObs();
	}
	
	
}
