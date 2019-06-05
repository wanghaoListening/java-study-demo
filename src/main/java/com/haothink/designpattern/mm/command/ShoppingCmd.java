package com.haothink.designpattern.mm.command;

public class ShoppingCmd extends Command{

	@Override
	public void excute() {
	
		System.out.println("execute");
	}

	@Override
	public void undo() {
	
		System.out.println("Undo");
	}
	
}
