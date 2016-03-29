package com.haothink.designpattern.template;
/*模版方法模式的具体要素
 * 1抽象基类
 * 		1.基本方法
 * 		2.抽象方法
 * 		3.可选的钩子
 * 		4.template方法（final）
 * 2具体子类
 * 		1.实现基类的抽象方法
 * 		2.覆盖钩子方法
 * 
 * */
public class MainStore {
	public static void main(String[] args) {
		DrinkProcess dp = new Drinkjuice();
		dp.makeDrink();
	}
}
