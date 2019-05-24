package com.haothink.string;
/*
 * jdk1.7�е�intern()ʵ�ֲ��Ḵ��ʵ�����������У�ֻ���ڳ������м�¼
 * �״γ��ֵ��ַ�����ʵ�������ã�����str1����true������������������Java
 * ����ַ����ԣ�������intern()���״γ���ԭ�򣬹ʷ���false
 * */
public class StringPool {
	public static void main(String[] args) {
		String str1 = new StringBuilder("�������").append("����").toString();
		System.out.println(str1.intern() == str1);
		
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);
		
	}
}
