package com.haothink.sequence;

import java.util.Arrays;

/**
 * 简单的选择排序
 * 算法思想：第一趟排序时，从第一个记录开始，通过n-1次关键字比较，从n个记录中选出，
 * 关键字最小的记录，并和第一个记录交换
 * 第二趟排序，从第二个记录开始，通过n-2次关键字的比较，从n-1个记录中选出关键字最小的
 * 记录与第二个记录交换
 * 时间复杂度为：O(n^2)
 * 空间复杂度暂且认为是O(2)其实可以将数组的最前一个元素空出作为交换的空间
 * */
public class SimSelectSortDemo {
	
	public static void main(String[] args) {
		int[] data = new int[]{49,38,65,97,76,13,27,49,54,04};
		selectSort(data);
		System.out.println("排序后为"+Arrays.toString(data));
	}
		
	public static void selectSort(int[] data){
		//记录最小元素的角标
		int index = 0;
		//交换元素的中间值
		int temp = 0;
		for(int i=0;i<=data.length-1;i++){
			//将开头需要比较的元素存储在x中
			temp = data[i];
			//存储第一个元素的角标
			index = i;
			for(int j=i;j<=data.length-1;j++){
				//当后边元素小于x中存储的元素则将新元素存储到x中继续向后比较
				if(temp>data[j]){
					index = j;
					temp=data[index];
				}
			}
			//一趟比较结束后将最小的元素与第i个元素交换位置
			data[index] = data[i];
			data[i] = temp;
		}
	}
}
