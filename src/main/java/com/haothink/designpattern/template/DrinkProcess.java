package com.haothink.designpattern.template;

public abstract class DrinkProcess {

	public final void makeDrink(){

		boilingWater();

		if(cusAdree())
		putSpices();

		joinMaterial();

		making();

		toCustomer();		

	}

	protected boolean cusAdree() {
		
		return true;
	}

	protected abstract void joinMaterial();
	

	private void toCustomer() {
		System.out.println("--");
		
	}

	protected abstract void making();
	

	protected abstract void putSpices();
			

	private void boilingWater() {
		System.out.println("boil water");
		
	}
}
