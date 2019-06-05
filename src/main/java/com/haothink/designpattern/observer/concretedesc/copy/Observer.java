package com.haothink.designpattern.observer.concretedesc.copy;
/**
 * 这是一个观察着接口，定义一个更新的接口给哪些在目标发生改变时被通知的对象
 * @author wanghao
 * */
public interface Observer {

	public void update(WeatherSubject subject);

	
	
}
