package com.haothink.designpattern.adapter;
/*
 * 将一个类的接口。转换成客户期望的另一个接口，
 * 使原本由于接口不兼容而不能一起工作的哪些类可以在一起工作
 * */
public class  AdapterTrans implements ThreePlugIf{

	private GBTwoPlug plug;
	
	public AdapterTrans(GBTwoPlug plug){
		this.plug = plug;
	}
	@Override
	public void powerSupply() {
	
		plug.powerSupply();
	}

}
