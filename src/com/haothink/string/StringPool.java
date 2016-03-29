package com.haothink.string;
/*
 * jdk1.7中的intern()实现不会复制实例到常量池中，只是在常量池中记录
 * 首次出现的字符串的实例的引用，索引str1返回true，而常量池中早已有Java
 * 这个字符所以，不符合intern()的首次出现原则，故返回false
 * */
public class StringPool {
	public static void main(String[] args) {
		String str1 = new StringBuilder("软件工程").append("导论").toString();
		System.out.println(str1.intern() == str1);
		
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
		
	}
}
