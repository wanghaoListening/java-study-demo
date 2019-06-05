package com.haothink.thread.concurrent.semaphore;
@SuppressWarnings("unused")
public class Fat {
	
	private volatile double d;
	private static int counter = 0;
	private final int id = counter++;
	public Fat(){
		
		for(int i=1;i<10000;i++){
			//Math.E ���κ�����ֵ�����ӽ� e������Ȼ�����ĵ������� double ֵ��
			d += (Math.PI + Math.E)/(double)i;
		}
	}
	public void operation(){
		System.out.println(this);
	}
	public String toString(){
		return "Fat id=" + id;
	}
}
