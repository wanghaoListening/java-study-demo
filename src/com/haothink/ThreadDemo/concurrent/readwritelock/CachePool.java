package com.haothink.ThreadDemo.concurrent.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 使用读写锁实现简易的缓存系统。
 * 
 * 相比Java中的锁(Locks in Java)里Lock实现，读写锁更复杂一些。
 * 假设你的程序中涉及到对一些共享资源的读和写操作，且写操作没有读操作那么频繁。
 * 在没有写操作的时候，两个线程同时读一个资源没有任何问题，
 * 所以应该允许多个线程能在同时读取共享资源。但是如果有一个线程想去写这些共享资源，
 * 就不应该再有其它线程对该资源进行读或写（译者注：也就是说：读-读能共存，读-写不能共存，
 * 写-写不能共存）。这就需要一个读/写锁来解决这个问题
 * */
public class CachePool {
	private Map<String,Object> caches = new HashMap<String,Object>();
	private ReadWriteLock rwLock = new ReentrantReadWriteLock(); 
	public Object getData(String key){

		rwLock.readLock().lock();
		Object value = null;
		try{
			value = caches.get(key);
			if(value == null){
				rwLock.readLock().unlock();
				rwLock.writeLock().lock();
				try{
					if(value == null){
						value = "从数据库查询数据";
						caches.put(key, value);
					}
				}finally{
					rwLock.writeLock().unlock();

				}
				rwLock.readLock().lock();
			}

		}finally{
			rwLock.readLock().unlock();
		}

		return value;
	}
}
