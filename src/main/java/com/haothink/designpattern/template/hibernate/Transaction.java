package com.haothink.designpattern.template.hibernate;

public class Transaction {
	
	public void commit(){
		System.out.println("commit");
	}
	
	public void rollback(){
		System.out.println("rollback");
	}
}
