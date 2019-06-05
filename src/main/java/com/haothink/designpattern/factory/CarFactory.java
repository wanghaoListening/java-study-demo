package com.haothink.designpattern.factory;

public class CarFactory extends Factory{

	@Override
	public Moveable create() {
		
		return new Car();
	}

}
