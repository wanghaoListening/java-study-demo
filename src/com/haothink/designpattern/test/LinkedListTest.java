package com.haothink.designpattern.test;

import java.util.LinkedList;

public class LinkedListTest {
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("wert");
		list.addFirst("qrer");
		list.addFirst("tdfg");
		
		System.out.println(list.removeFirst());
		System.out.println(list.removeFirst());
		System.out.println(list.removeFirst());
	}
}
