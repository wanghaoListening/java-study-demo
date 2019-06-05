package com.haothink.designpattern.iterator;

public class Container implements ICollection{
	private Object[] objs = new Object[10];
	private int index=0;
	@Override
	public void add(Object obj){
		if(objs.length==index){
			Object[] newObjs = new Object[index+index/2];
			System.arraycopy(objs, 0, newObjs, 0, objs.length);
			objs = newObjs;
		}
		objs[index] = obj;
		index++;
	}

	@Override
	public int size(){
		return index;
	}
	@Override
	public IIterator iiterator() {
		
		return new Itr();
	}
	
	private class Itr implements IIterator{
		int cursor;

		@Override
		public Object next() {

			return objs[cursor++];
		}

		@Override
		public boolean hasNext() {
			return cursor!=size();
		}
		
	}
}
