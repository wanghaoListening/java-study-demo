package com.haothink.designpattern.template.hibernate;

public class Session {
	
	public void save(Object obj){
		String name = obj.getClass().getName();
		System.out.println(name);
	}

	public Transaction beginTransaction() {
		System.out.println("开事物");
		return new Transaction();
	}

	public Transaction getTransaction() {
		
		return new Transaction();
	}
	
	
}
