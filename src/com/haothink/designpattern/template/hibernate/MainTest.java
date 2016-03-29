package com.haothink.designpattern.template.hibernate;

import java.util.Date;

public class MainTest {
	public static void main(String[] args) {
		HibernateTemplate ht = new HibernateTemplate();
		ht.save(new Date());
	}
}
