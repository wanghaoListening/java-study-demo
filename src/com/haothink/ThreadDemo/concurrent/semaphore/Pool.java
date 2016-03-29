package com.haothink.ThreadDemo.concurrent.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
/**
 * 
 * 一个计数信号量。从概念上讲，信号量维护了一个许可集。如有必要，
 * 在许可可用前会阻塞每一个 acquire()，然后再获取该许可。每个 release() 
 * 添加一个许可，从而可能释放一个正在阻塞的获取者。但是，不使用实际的许可对象，
 * Semaphore 只对可用许可的号码进行计数，并采取相应的行动。 
 *boolean类型的数组checkedOut可以跟踪被签出的对象，并且通过getItem和releaseItem
 *方法来进行管理。而这些都将由Semaphore类型的available来加以解决，因此，在checkOut()
 *中，如果没有任何信号量许可证可用(这意味着线程池没有更多的对象了)，available将阻塞调用过程
 *在checkIn()中，如果被签入的对象有效，则向信号量返回一个许可证。
 *
 * */
public class Pool<T> {
	private int size;
	private List<T> items = new ArrayList<T>();
	private volatile boolean[] checkedOut;
	private Semaphore available;
	public Pool(Class<T> classObject,int size){
		this.size = size;
		checkedOut = new boolean[size];
		/*fair - 如果此信号量保证在争用时按先进先出的顺序授予许可，则为 true；否则为 false。
		方法详细信息 
			new Semaphore(size,true);
		*/
		available = new Semaphore(size);
		// Load pool with objects that can be checked out
		for(int i=0;i<size;++i)
			try{
				//Assumes a default constructor
				items.add(classObject.newInstance());
			}catch(Exception e){
				throw new RuntimeException();
			}
	}
	
	public T checkOut() throws InterruptedException{
		//从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。
		available.acquire();
		return getItem();
	}
	
	public void checkIn(T x){
		if(releaseItem(x))
			//释放一个许可，将其返回给信号量。
			System.out.println("签入了");
			available.release();
	}

	private synchronized boolean releaseItem(T item) {
		/* 返回此列表中最后一次出现的指定元素的索引，或如果此列表不包含索引，则返回 -1。*/
		int index = items.indexOf(item);
		if(index == -1)
			return false;
		if(checkedOut[index]){
			checkedOut[index] = false;
			return true;
		}
		return false;//Wasn't checked out 
	}

	private synchronized T getItem() {
	   for(int i=0;i<size;++i)
		   if(!checkedOut[i]){
			   checkedOut[i] = true;
			   return items.get(i);
		   }
		return null;// Semaphore prevents reaching here
	}
}
