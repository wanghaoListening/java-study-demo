package com.haothink.designpattern.iterator;

public interface ICollection {
	
	public IIterator iiterator();
	
	public void add(Object obj);
	
	public int size();
}
