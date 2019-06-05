package com.haothink.designpattern.proxy;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
@SuppressWarnings("rawtypes")
public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager sjf = jc.getStandardFileManager(null, null, null);
	
		Iterable it = sjf.getJavaFileObjects("G:\\MoveProxy.java");
		CompilationTask ct = jc.getTask(null, sjf, null, null, null, it);
		ct.call();
		sjf.close();
		System.out.println(jc.getClass().getName());
		
		//load into memory and create an instance
		URL[] urls = new URL[]{new URL("file:/"+"G:/")};
		@SuppressWarnings("resource")
		URLClassLoader url = new URLClassLoader(urls);
		Class c = url.loadClass("MoveProxy");
		System.out.println(c);
	}
}
