package com.haothink.sequence;

import java.util.Arrays;

/**
 * 冒泡排序
 * 此排序是：稳定排序
 * 它是通过对相邻的数据元素进行交换，逐步将待排序列变成有序序列的过程。
 * 第一趟排序后把最大的元素排到最后头。再对前n-1个元素进行排序。
 * 冒泡排序的时间复杂度是O（n^2）
 * 空间复杂度为O（1）
 * */
public class BubbleSortDemo {
	
	
	public static void main(String[] args) {
		int[] data = new int[]{49,38,65,97,76,13,27,49,54,04};
		bubbleSort(data);
		System.out.println(Arrays.toString(data));
	}
	
	public static void bubbleSort(int[] data){
		int temp=0;//用作数据交换时的存储空间
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

