package com.haothink.designpattern.observer.concretedesc;

public class ConcreteObserver implements Observer{

	private String name;

	private String weatherContent;

	private String content;

	@Override
	public void update(WeatherSubject subject) {
		
		weatherContent = ((ConcreteWeatherSubject)subject).getSubjectState();
		System.out.println(name+weatherContent+","+content);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeatherContent() {
		return weatherContent;
	}
	public void setWeatherContent(String weatherContent) {
		this.weatherContent = weatherContent;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	

}
