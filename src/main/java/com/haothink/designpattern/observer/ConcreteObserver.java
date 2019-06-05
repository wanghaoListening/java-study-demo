package com.haothink.designpattern.observer;


@SuppressWarnings("unused")
public class ConcreteObserver implements Observer{

	private String observerState;

	@Override
	public void update(Subject subject) {
		
		observerState = ((ConcreteSubject)subject).getSubjectState();
	}

}
