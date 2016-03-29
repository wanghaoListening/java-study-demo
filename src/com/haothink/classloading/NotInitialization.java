package com.haothink.classloading;
@SuppressWarnings("unused")
public class NotInitialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(SubClass.value);

		SuperClass[] sc = new SuperClass[5];
		String str = "me.jpg";
		String[] strArray = str.split("\\.");
		String endName = strArray[strArray.length-1];
	}

	public boolean isExistName(String endName){

		switch(endName){
		case "txt" : return true;
		case "xls" : return true;
		case "xlsx" : return true;
		case "docx" : return true;
		case "ppt" : return true;
		case "pptx" : return true;
		case "jpg" : return true;
		default : return false;
		}
	}

}
