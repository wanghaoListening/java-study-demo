package com.haothink.designpattern.proxy.jdkproxyprinciple;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

/*
 * jdk动态代理实现思路
 * 1声明一段源码（动态产生代理）
 * 2编译源码（JDK Compiler API）,(产生新的代理类)
 * 3将这个类load到内存中。产生一个新的对象
 * return返回代理对象
 * */
public class Proxy {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object newProxyInstance(Class infec,InvocationHandler h) throws Exception{
		String rt = "\r\n";
		
		String methodStr = "";
		for(Method m : infec.getMethods()){
			methodStr +=
			"	@Override" + rt +
			"	public void "+m.getName()+"() {" + rt +
			"      try{" +rt+
			"        Method md = "+infec.getName()+".class.getMethod(\""	
							+m.getName()+"\");"+ rt +
			" 	     h.invoke(this, md);"+ rt +	
			"      }"+ rt +	
			"      catch(Exception e){"+ rt +
			"            e.printStackTrace();"+ rt +
			"      }"+ rt +
			"	}";
		}
		String str = 
		"package com.haothink123.proxy;" + rt +	
		"import "+infec.getName()+";" + rt +
		"import java.lang.reflect.Method;" + rt +
		"import com.haothink123.proxy.jdkproxy.InvocationHandler;" + rt +
		"public class $Proxy0 implements "+infec.getName()+"{" + rt +
			
		"private "+infec.getName()+" movie;" + rt +
		"private InvocationHandler h;" + rt +	
			
		"	public $Proxy0() {" + rt +
		"		super();" + rt +
				
		"	}" + rt +
			
		"	public $Proxy0(InvocationHandler h) {" + rt +
		"		super();" + rt +
		"		this.h = h;" + rt +
		"	}" + rt +

		methodStr + rt +

		"}" + rt ;
		//得到当前应用的路径
		String filename = System.getProperty("user.dir")
				+"/bin/com/haothink123/proxy/$Proxy0.java";
		//产生一个Java文件
		File file = new File(filename);
		FileOutputStream fin = new FileOutputStream(file);
		fin.write(str.getBytes());
		fin.close();
		
		//编译Java文件
		//拿到编译器(与命令行下javac编译相同)
		JavaCompiler  complier = ToolProvider.getSystemJavaCompiler();
		//得到文件管理器
		StandardJavaFileManager sjf = complier.getStandardFileManager(null, null, null);
		//获取文件
		Iterable it = sjf.getJavaFileObjects(filename);
		//得到编译任务
		CompilationTask ct = complier.getTask(null, sjf, null, null, null, it);
		ct.call();//编译
		sjf.close();
		
		//load 到内存当中
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		//把class文件load到内存当中
		Class c = cl.loadClass("com.haothink123.proxy.$Proxy0");

		//根据构造器来创建代理类
		Constructor ctr = c.getConstructor(InvocationHandler.class);
		
		return ctr.newInstance(h);
		
	}
}
