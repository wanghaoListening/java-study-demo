package com.haothink.string;

public class HashDemo {
	public static void main(String[] args) {
		String s = new String("abcdef");
		String m = "abcdef";
		System.out.println(s.equals(m));
		System.out.println(s == m);
		System.out.println(s.hashCode());
		System.out.println(m.hashCode());
	}
}
