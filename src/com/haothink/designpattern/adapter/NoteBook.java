package com.haothink.designpattern.adapter;

public class NoteBook {
	
	private ThreePlugIf plugIf ;
	public NoteBook(ThreePlugIf plugIf){
		this.plugIf = plugIf;
	}
	public static void main(String[] args) {
		GBTwoPlug gbTwoPlug = new GBTwoPlug();
		ThreePlugIf plug = new AdapterTrans(gbTwoPlug);
		NoteBook n = new NoteBook(plug);
		n.charge();
	}
	
	public void charge(){
		plugIf.powerSupply();
	}
}
