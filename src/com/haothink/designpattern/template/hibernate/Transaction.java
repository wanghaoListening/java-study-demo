package com.haothink.designpattern.template.hibernate;

public class Transaction {
	
	public void commit(){
		System.out.println("事务的提交");
	}
	
	public void rollback(){
		System.out.println("对事物进行回滚");
	}
}
