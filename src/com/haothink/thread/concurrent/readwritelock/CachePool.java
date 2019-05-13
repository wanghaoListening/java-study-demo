package com.haothink.thread.concurrent.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * ʹ�ö�д��ʵ�ּ��׵Ļ���ϵͳ��
 * 
 * ���Java�е���(Locks in Java)��Lockʵ�֣���д��������һЩ��
 * ������ĳ������漰����һЩ������Դ�Ķ���д��������д����û�ж�������ôƵ����
 * ��û��д������ʱ�������߳�ͬʱ��һ����Դû���κ����⣬
 * ����Ӧ���������߳�����ͬʱ��ȡ������Դ�����������һ���߳���ȥд��Щ������Դ��
 * �Ͳ�Ӧ�����������̶߳Ը���Դ���ж���д������ע��Ҳ����˵����-���ܹ��棬��-д���ܹ��棬
 * д-д���ܹ��棩�������Ҫһ����/д��������������
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
						value = "�����ݿ��ѯ����";
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
