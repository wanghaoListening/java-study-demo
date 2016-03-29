package com.haothink.ThreadDemo.concurrent.hashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BeanManage {

	private ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();

	public Object getBean(String key){
		Object bean = map.get(key);
		if(bean == null) {
			map.putIfAbsent(key, new Object());
			bean = map.get(key);
		}
		return bean;
	}
}
