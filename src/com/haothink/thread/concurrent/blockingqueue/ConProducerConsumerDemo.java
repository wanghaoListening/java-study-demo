package com.haothink.thread.concurrent.blockingqueue;

/**
 * ģ����������
 * */
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConProducerConsumerDemo {
	public static void main(String[] args) {
		MyBlockingQuery queue = new MyBlockingQuery();
		new Thread(new Producer(queue)).start();
		new Thread(new Consumer(queue)).start();

	}
}

class MyBlockingQuery {
	private static final int COUNT = 10;
	private Lock lock = new ReentrantLock();
	/** Condition for waiting takes */
	private Condition notEmpty = lock.newCondition();
	/** Condition for waiting puts */
	private Condition notFull = lock.newCondition();
	/**ʹ��linkedList����ʵ�ֶ��нṹ*/
	private LinkedList<Product> products = new LinkedList<Product>();
	
	public void put(Product product) throws InterruptedException{
		 if (product == null) throw new NullPointerException();
		 /**�����ǰ�߳�δ���жϣ����ȡ����*/
		lock.lockInterruptibly();
		
		 try {
		        try {
		            while (COUNT == products.size())
		                notFull.await();
		        } catch (InterruptedException ie) {
		            notFull.signal(); // propagate to non-interrupted thread
		            throw ie;
		        }
		        insert(product);
		    } finally {
		        lock.unlock();
		    }
	}
	
	private void insert(Product x) {
		products.offerLast(x);//���б�ĩβ����һ��Ԫ��
	    notEmpty.signal();
	}
	
	public Product take() throws InterruptedException{
		lock.lockInterruptibly();
	    try {
	        try {
	            while (products.size() == 0)
	            	 notEmpty.await();
	        } catch (InterruptedException ie) {
	            notEmpty.signal(); // propagate to non-interrupted thread
	            throw ie;
	        }
	        Product x = extract();
	        return x;
	    } finally {
	        lock.unlock();
	    }
	}

	private Product extract() {
		Product product = products.pollFirst();
		notFull.signal();
		return product;
	}
	
	public synchronized int size(){
		return products.size();
	}
	
}

class Product {
	private String name;
	private int count = 1;
	
	public Product(int count){
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}

class Producer implements Runnable{
	private final MyBlockingQuery queue;
	
	public Producer(MyBlockingQuery queue){
		this.queue = queue;
	}
	@Override
	public void run() {
		for(int i=1;i<=10;i++){
			try {
				queue.put(new Product(i));
				System.out.println("product:"+i);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		
		}
	}	
}

class Consumer implements Runnable{
	private final MyBlockingQuery queue;
	public Consumer(MyBlockingQuery queue){
		this.queue = queue;
	}
	@Override
	public void run() {
		while(true){
			try {
				Product p = queue.take();
				System.out.println("consumeһ��"+(p.getCount()));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
	
}


