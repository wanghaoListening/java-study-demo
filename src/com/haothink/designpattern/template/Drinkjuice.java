package com.haothink.designpattern.template;

public class Drinkjuice extends DrinkProcess{

	@Override
	protected void joinMaterial() {
		System.out.println("加入橘子汁");
		
	}

	@Override
	protected void making() {
		
		System.out.println("开始制作了。。。");
	}

	@Override
	protected void putSpices() {
		
		System.out.println("放入香料");
	}
	/*
	 * 子类通过覆盖的形式选择挂载钩子函数
	 * */
	@Override
	protected boolean cusAdree() {
		
		return false;
	}

}
