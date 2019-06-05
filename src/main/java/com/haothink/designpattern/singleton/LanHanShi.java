package com.haothink.designpattern.singleton;
/*
 * 
 * 
 * */
public class LanHanShi {
	
	private static volatile LanHanShi lanHanShi = null;
	
	private LanHanShi(){
		
	}
	
	public static LanHanShi getInstance(){
	     if(lanHanShi == null){	
		synchronized(LanHanShi.class){
		    if(lanHanShi == null){
			lanHanShi = new LanHanShi();
		    }
		}
	     }
	     return lanHanShi;
	}
}
