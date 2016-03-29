package com.haothink.designpattern.iterator;

public class MainTest {
	public static void main(String[] args) {
		Container cl = new Container();

		for(int i=0;i<=9;i++){
			cl.add(new People("wang"+i,i*10+i));
		}
		System.out.println(cl.size());
		IIterator iit = cl.iiterator();
		while(iit.hasNext()){
			System.out.println(iit.next());
		}
	}
}
