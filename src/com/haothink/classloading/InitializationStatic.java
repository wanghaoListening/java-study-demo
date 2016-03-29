package com.haothink.classloading;
/**
 * 静态语句块中只能访问到定义在静态语句块之前的变量
 * 定义在它之后的变量，前面的静态语句块可以赋值，但不能访问
 * 
 * */
public class InitializationStatic {
	static{
		f =1;
		//System.out.println(f);
	}
	
	public static int f=0;
	
}
