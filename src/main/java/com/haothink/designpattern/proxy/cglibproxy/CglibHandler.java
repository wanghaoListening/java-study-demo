package com.haothink.designpattern.proxy.cglibproxy;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy; 
public class CglibHandler implements MethodInterceptor{
	private Enhancer enhancer = new Enhancer();
	@SuppressWarnings("rawtypes")
	public Object getProxy(Class clazz){

		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("-----------CglibHandler");

		proxy.invokeSuper(obj, args);
		System.out.println("-----------CglibHandler");
		return null;
	}

}
