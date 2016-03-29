package com.haothink.designpattern.mm.bridge;

public class MM {
	
	public boolean getGift(Gift gift){
		if(gift != null)
			return true;
		return false;
	}
}
