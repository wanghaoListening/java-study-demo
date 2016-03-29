package com.haothink.gc;

public class JavaVMStackSOF {
	
	private int stackLength = 1;
	
	public void stackLeak(){
		stackLength++;
		stackLeak();
		
	}
	
	public static void main(String[] args) {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
			//因为栈溢出是个err而不是exception所以用Throwable
		} catch (Throwable e) {
			System.out.println("深度为  " + oom.stackLength);
			throw e;
		}
	}
	
}
