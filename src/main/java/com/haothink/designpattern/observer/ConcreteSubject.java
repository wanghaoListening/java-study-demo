package com.haothink.designpattern.observer;

public class ConcreteSubject extends Subject {	
	private String subjectState;

	public String getSubjectState() {
		return subjectState;
	}
	/**
	 * 当目标者状态改变时提醒观察者 
	 * */
	public void setSubjectState(String subjectState) {
		this.subjectState = subjectState;
		this.notifyObs();
	}
		
}
