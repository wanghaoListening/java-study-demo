package com.haothink.designpattern.mm.bridge;
/**
 * 所谓耦合，就是两个实体的行为的某种强关联。而将它们的强关联去掉，就是耦合的解脱，
 * 或称脱耦。在这里，脱耦是指将抽象化和实现化之间的耦合解脱开，或者说是将它们之间
 * 的强关联改换成弱关联。
 * 将两个角色之间的继承关系改为聚合关系，就是将它们之间的强关联改换成为弱关联。
 * 因此，桥梁模式中的所谓脱耦，就是指在一个软件系统的抽象化和实现化之间使用组合/聚
 * 合关系而不是继承关系，从而使两者可以相对独立地变化。这就是桥梁模式的用意。
 * */
public class Boy {
	
	private String name;
	
	public void pursue(MM mm){
		Gift gift = new Ordinary(new Phone());
		boolean b = mm.getGift(gift);
		System.out.println(b);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
