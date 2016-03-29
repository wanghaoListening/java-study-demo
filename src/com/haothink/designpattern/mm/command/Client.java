package com.haothink.designpattern.mm.command;

public class Client {
	
	public static void main(String[] args) {
		Boy b = new Boy();
		MM m = new MM();
		m.askBoy(b);
	}
}
