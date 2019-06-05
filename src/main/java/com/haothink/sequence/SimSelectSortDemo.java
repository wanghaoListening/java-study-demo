package com.haothink.sequence;

import java.util.Arrays;


public class SimSelectSortDemo {
	
	public static void main(String[] args) {
		int[] data = new int[]{49,38,65,97,76,13,27,49,54,04};
		selectSort(data);
		System.out.println(Arrays.toString(data));
	}
		
	public static void selectSort(int[] data){

		int index = 0;

		int temp = 0;
		for(int i=0;i<=data.length-1;i++){

			temp = data[i];

			index = i;
			for(int j=i;j<=data.length-1;j++){

				if(temp>data[j]){
					index = j;
					temp=data[index];
				}
			}

			data[index] = data[i];
			data[i] = temp;
		}
	}
}
