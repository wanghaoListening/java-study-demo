package com.haothink.designpattern.singleton;
/*
 * 
 * 
 * */
public class LanHanShi {
	
	private LanHanShi lanHanShi;
	
	private LanHanShi(){
		
	}
	
	public synchronized LanHanShi getInstance(){
		if(lanHanShi == null){
			lanHanShi = new LanHanShi();
		}
		
		return lanHanShi;
	}
}
