package com.haothink.thread.concurrent.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
						value = "";
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
