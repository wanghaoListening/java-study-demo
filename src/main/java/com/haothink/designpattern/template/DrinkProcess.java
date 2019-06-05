package com.haothink.designpattern.template;
/*
 *     模板方法模式：模板方法模式准备一个抽象类，将部分逻辑以具体方法以及具体构造子的
 *     形式实现，然后声明一些抽象方法来迫使子类实现剩余的逻辑。不同的子类可以以不同的
 *     方式实现这些抽象方法，从而对剩余的逻辑有不同的实现。先制定一个顶级逻辑框架，而
 *     将逻辑的细节留给具体的子类去实现。开闭原则是指一个软件实体应该对扩展开放，对修
 *     改关闭。也就是说软件实体必须是在不被修改的情况下被扩展。模板方法模式意图是由抽
 *     象父类控制顶级逻辑，并把基本操作的实现推迟到子类去实现,这是通过继承的手段来达
 *     到对象的复用，同时也遵守了开闭原则！
 * */
public abstract class DrinkProcess {
	//此方法为final不能被子类继承用于统一调用子类中不同的实现
	public final void makeDrink(){
		//1煮热水
		boilingWater();
		//2加入香料
		if(cusAdree())
		putSpices();
		//3加入原料
		joinMaterial();
		//4开始制作
		making();
		//5呈给顾客
		toCustomer();		

	}
	/*HOOK钩子方法,protected权限可被子类覆盖，默认为返回true 
	 *询问顾客是否要加入香料
	 *提供一个默认或空的实现，具体子类自行解决如何挂钩 
	 * */ 
	protected boolean cusAdree() {
		
		return true;
	}

	protected abstract void joinMaterial();
	

	private void toCustomer() {
		System.out.println("呈给顾客");
		
	}

	protected abstract void making();
	

	protected abstract void putSpices();
			

	private void boilingWater() {
		System.out.println("开始煮热水");
		
	}
}
