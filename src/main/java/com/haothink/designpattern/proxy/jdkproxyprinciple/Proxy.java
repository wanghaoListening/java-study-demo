package com.haothink.designpattern.proxy.jdkproxyprinciple;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

/**
 *
 *
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

		String filename = System.getProperty("user.dir")
				+"/bin/com/haothink123/proxy/$Proxy0.java";

		File file = new File(filename);
		FileOutputStream fin = new FileOutputStream(file);
		fin.write(str.getBytes());
		fin.close();

		JavaCompiler  complier = ToolProvider.getSystemJavaCompiler();

		StandardJavaFileManager sjf = complier.getStandardFileManager(null, null, null);

		Iterable it = sjf.getJavaFileObjects(filename);

		CompilationTask ct = complier.getTask(null, sjf, null, null, null, it);
		ct.call();
		sjf.close();
		
		//load
		ClassLoader cl = ClassLoader.getSystemClassLoader();

		Class c = cl.loadClass("com.haothink123.proxy.$Proxy0");


		Constructor ctr = c.getConstructor(InvocationHandler.class);
		
		return ctr.newInstance(h);
		
	}
}
