package com.haothink.designpattern.proxy.cglibproxy;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy; 
public class CglibHandler implements MethodInterceptor{
	private Enhancer enhancer = new Enhancer();
	@SuppressWarnings("rawtypes")
	public Object getProxy(Class clazz){
		//clazz需要被代理的类
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	/*1.拦截所有目标类方法的调用
	 * obj目标类实例
	 * method目标方法的反射对象
	 * args方法的参数
	 * proxy代理类实例
	 *  
	 * */
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("-----------CglibHandler");
		//代理类调用父类的方法
		proxy.invokeSuper(obj, args);
		System.out.println("-----------CglibHandler");
		return null;
	}

}
