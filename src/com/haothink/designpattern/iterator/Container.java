package com.haothink.designpattern.iterator;
/*
 * 模拟jdk中arraylist集合
 * */
public class Container implements ICollection{
	Object[] objs = new Object[10];
	int index=0;
	public void add(Object obj){
		if(objs.length==index){
			Object[] newObjs = new Object[index+index/2];
			//把旧数组的引用copy到新的数组
			System.arraycopy(objs, 0, newObjs, 0, objs.length);
			//将新数组的引用赋给旧数组
			objs = newObjs;
		}
		objs[index] = obj;
		index++;
	}
	//返回目前数组元素的长度
	public int size(){
		return index;
	}
	@Override
	public IIterator iiterator() {
		
		return new Itr();
	}
	
	private class Itr implements IIterator{
		int cursor;       // index of next element to return
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
