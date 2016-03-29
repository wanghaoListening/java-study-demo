package com.haothink.designpattern.iterator;

import org.junit.Test;

public class TestCon {
	@Test
	public void testConllLinked(){
		CollLinked cl = new CollLinked();
		
		for(int i=0;i<=9;i++){
			cl.add(new People("wang"+i,i*10+i));
		}
		System.out.println(cl.size());
		IIterator iIterator = cl.iiterator();
		while(iIterator.hasNext()){
			System.out.println(iIterator.next());
		}
	}
	
	@Test
	public void testContainer(){
		Container cl = new Container();
		
		for(int i=0;i<=9;i++){
			cl.add(new People("wang"+i,i*10+i));
		}
		System.out.println(cl.size());
		IIterator iit = cl.iiterator();
		while(iit.hasNext()){
			System.out.println((People)iit.next());
		}
	}
	
	
}
