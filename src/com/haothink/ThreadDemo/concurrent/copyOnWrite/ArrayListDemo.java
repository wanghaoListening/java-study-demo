package com.haothink.ThreadDemo.concurrent.copyOnWrite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ArrayListDemo {
	public static void main(String[] args) {
		Collection<String> conns = new ArrayList<String>();
		conns.add("Doug Lea");
		conns.add("Joshua Bloch");
		conns.add("Bruce Edition");
		
		Iterator<String> its = conns.iterator();
		while(its.hasNext()){
			String name = its.next();
			System.out.println("before"+name);
			if("Doug Lea".equals(name)){
				conns.remove(name);
			}
			System.out.println("after"+name);
			
		}
	}
}
