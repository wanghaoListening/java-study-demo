package com.haothink.designpattern.mm.command;

public class ShoppingCmd extends Command{

	@Override
	public void excute() {
	
		System.out.println("去购物");
	}

	@Override
	public void undo() {
	
		System.out.println("不想去购物");
	}
	
}
