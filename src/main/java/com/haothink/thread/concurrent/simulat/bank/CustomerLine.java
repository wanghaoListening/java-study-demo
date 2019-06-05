package com.haothink.thread.concurrent.simulat.bank;

import java.util.concurrent.ArrayBlockingQueue;

public class CustomerLine extends ArrayBlockingQueue<Customer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerLine(int capacity) {
		super(capacity);
		
	}
	
	//Teach the customer line to display itselt
	public String toString(){
		if(this.size() == 0)
			return "[Empty]";
		StringBuilder result = new StringBuilder();
		for(Customer customer : this)
			result.append(customer);
		return result.toString();
	}

}
