package com.haothink.designpattern.mm.bridge;

public class Ordinary extends Gift{
	
	public Ordinary(GiftImpl imple){
		this.imple = imple;
	}
}
