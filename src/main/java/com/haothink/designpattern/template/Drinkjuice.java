package com.haothink.designpattern.template;

public class Drinkjuice extends DrinkProcess{

	@Override
	protected void joinMaterial() {
		System.out.println("joinMaterial");
		
	}

	@Override
	protected void making() {
		
		System.out.println("making");
	}

	@Override
	protected void putSpices() {
		
		System.out.println("putSpices");
	}
	/*

	 * */
	@Override
	protected boolean cusAdree() {
		
		return false;
	}

}
