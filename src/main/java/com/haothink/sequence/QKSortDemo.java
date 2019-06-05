package com.haothink.sequence;

import java.util.Arrays;


/**
 * 快速排序
 */
public class QKSortDemo {
	
	public static void main(String[] args) {
		int[] data = new int[]{49,38,65,97,76,13,27,49,54,04};
		quick(data);
		System.out.println(Arrays.toString(data));
	}
	
	public static void quick(int[] numbers)
	{
		if(numbers.length > 0)
		{
			quickSort(numbers, 0, numbers.length-1);
		}
	}
	
	public static void quickSort(int[] numbers,int low,int high)
	{
		if(low < high)
		{
			int middle = getMiddle(numbers,low,high);
			quickSort(numbers, low, middle-1);
			quickSort(numbers, middle+1, high);
		}

	}
	public static int getMiddle(int[] numbers, int low,int high)
	{
		int temp = numbers[low];
		while(low < high)
		{
			while(low < high && numbers[high] >= temp)
				high--;
			if(low<high){
				numbers[low]=numbers[high];
				low++;
			}
			while(low < high && numbers[low] < temp)
				low++;
			
			if(low<high){
				numbers[high]=numbers[low];
				high--;
			}
		}
		numbers[low] = temp ;
		return low ;
	}
	
	
}
