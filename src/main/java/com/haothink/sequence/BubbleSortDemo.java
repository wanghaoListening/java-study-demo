package com.haothink.sequence;

import java.util.Arrays;

/**
 * 冒泡排序
 * */
public class BubbleSortDemo {
	
	
	public static void main(String[] args) {
		int[] data = new int[]{49,38,65,97,76,13,27,49,54,04};
		bubbleSort(data);
		System.out.println(Arrays.toString(data));
	}
	
	public static void bubbleSort(int[] data){
		int temp=0;
		for(int i=1;i<=data.length-1;i++){
			for(int j=0;j<data.length-i;j++){
				if(data[j]>data[j+1]){
					temp = data[j];
					data[j] = data[j+1];
					data[j+1]= temp;
				}
			}
		}
	}
}

