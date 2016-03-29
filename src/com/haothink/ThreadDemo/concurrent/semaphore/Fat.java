package com.haothink.ThreadDemo.concurrent.semaphore;
@SuppressWarnings("unused")
public class Fat {
	
	private volatile double d;
	private static int counter = 0;
	private final int id = counter++;
	public Fat(){
		
		for(int i=1;i<10000;i++){
			//Math.E 比任何其他值都更接近 e（即自然对数的底数）的 double 值。
			d += (Math.PI + Math.E)/(double)i;
		}
	}
	public void operation(){
		System.out.println(this);
	}
	public String toString(){
		return "Fat id=" + id;
	}
}
