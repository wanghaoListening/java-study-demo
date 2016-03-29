package com.haothink.designpattern.template.hibernate;

public class Session {
	
	public void save(Object obj){
		String name = obj.getClass().getName();
		System.out.println("已经将"+name+"保存到数据库中");
	}

	public Transaction beginTransaction() {
		System.out.println("开启事务");
		return new Transaction();
	}

	public Transaction getTransaction() {
		
		return new Transaction();
	}
	
	
}
