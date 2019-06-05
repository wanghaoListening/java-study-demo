package com.haothink.designpattern.mm.command;
/*1.抽象方法不能有方法体
 *2.抽象类中要想写非抽象方法，必须要有一个抽象方法。
 * 在一个command模式的类中需要有有一个执行方法和一个回滚方法。
 * 用于“行为请求者”与“行为实现者”解耦，可实现二者之间的松耦合，
 * 以便适应变化。分离变化与不变的因素。
 * 在面向对象的程序设计中，一个对象调用另一个对象，一般情况下的调用过程是：
 * 创建目标对象实例；设置调用参数；调用目标对象的方法。
 *但在有些情况下有必要使用一个专门的类对这种调用过程加以封装，我们把这种专门的类称作command类。
 *java.lang.Runnable的所有实现
 *javax.swing.Action的所有实现

 * */
public abstract class Command {
	
	public abstract void excute();
	
	public abstract void undo();

}
