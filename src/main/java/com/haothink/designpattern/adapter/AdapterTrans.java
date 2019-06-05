package com.haothink.designpattern.adapter;

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
