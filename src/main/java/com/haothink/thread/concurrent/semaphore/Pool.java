package com.haothink.thread.concurrent.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
/**
 * 
 * һ�������ź������Ӹ����Ͻ����ź���ά����һ����ɼ������б�Ҫ��
 * ����ɿ���ǰ������ÿһ�� acquire()��Ȼ���ٻ�ȡ����ɡ�ÿ�� release() 
 * ���һ����ɣ��Ӷ������ͷ�һ�����������Ļ�ȡ�ߡ����ǣ���ʹ��ʵ�ʵ���ɶ���
 * Semaphore ֻ�Կ�����ɵĺ�����м���������ȡ��Ӧ���ж��� 
 *boolean���͵�����checkedOut���Ը��ٱ�ǩ���Ķ��󣬲���ͨ��getItem��releaseItem
 *���������й�������Щ������Semaphore���͵�available�����Խ������ˣ���checkOut()
 *�У����û���κ��ź������֤����(����ζ���̳߳�û�и���Ķ�����)��available���������ù���
 *��checkIn()�У������ǩ��Ķ�����Ч�������ź�������һ�����֤��
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
		/*fair - ������ź�����֤������ʱ���Ƚ��ȳ���˳��������ɣ���Ϊ true������Ϊ false��
		������ϸ��Ϣ 
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
		//�Ӵ��ź�����ȡһ����ɣ����ṩһ�����ǰһֱ���߳������������̱߳��жϡ�
		available.acquire();
		return getItem();
	}
	
	public void checkIn(T x){
		if(releaseItem(x))
			//�ͷ�һ����ɣ����䷵�ظ��ź�����
			System.out.println("ǩ����");
			available.release();
	}

	private synchronized boolean releaseItem(T item) {
		/* ���ش��б������һ�γ��ֵ�ָ��Ԫ�ص���������������б������������򷵻� -1��*/
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
