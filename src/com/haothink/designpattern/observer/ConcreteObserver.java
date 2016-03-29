package com.haothink.designpattern.observer;
@SuppressWarnings("unused")
public class ConcreteObserver implements Observer{	
	private String observerState;
	/**
	 * 获取目标者状态改变时同步到观察着
	 * */
	@Override
	public void update(Subject subject) {
		
		observerState = ((ConcreteSubject)subject).getSubjectState();
	}

}
