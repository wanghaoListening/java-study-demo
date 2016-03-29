package com.haothink.designpattern.mm.command;

public class MM {
	
	public void askBoy(Boy b){
		Command cmd = new ShoppingCmd();
		b.addCmd(cmd);
		b.executeCmd();
	}
}
