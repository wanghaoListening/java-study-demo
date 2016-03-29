package com.haothink.designpattern.observer.concretedesc;
/**
 * 拉模型的实现
 * */
public class ConcreteObserver implements Observer{
	//观察者的名字，是谁收到了这个讯息
	private String name;
	//天气内容情况从目标类获取
	private String weatherContent;
	//提醒的内容
	private String content;
	/**
	 * 获取目标者状态改变时同步到观察着
	 * */
	@Override
	public void update(WeatherSubject subject) {
		
		weatherContent = ((ConcreteWeatherSubject)subject).getSubjectState();
		System.out.println(name+"收到了"+weatherContent+","+content);
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
