package com.haothink.designpattern.singleton;
/*恶汉式单利模式演示
 * 1.私有的构造器
 * 3.指向自己实例的私有的静态引用
 * 3.返回自己对象实例的的公共静态方法
 * 
 * */
public class VillianType {
	
	private VillianType villianType = new VillianType();
	
	private VillianType(){}
	
	public VillianType getInstance(){
		
		return villianType;
	}
}
