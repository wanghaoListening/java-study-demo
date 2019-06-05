package com.haothink.sequence;

import java.util.Arrays;

/**
 *
 * 希尔排序
 *
 *
 * */
public class ShellInsertDemo {

	public static void main(String[] args) {
		int[] data = new int[]{49,38,65,97,76,13,27,49,54,04};
		shellSort(data);
		System.out.println(Arrays.toString(data));
	}
	public static void shellSort(int[] data) 
	{
		int j = 0;
		int temp = 0;

		for (int increment = data.length / 2; increment > 0; increment /= 2)
		{
			for (int i = increment; i < data.length; i++) 
			{
				temp = data[i];
				for (j = i; j >= increment; j -= increment) 
				{
					if(temp < data[j - increment])
					{   
						data[j] = data[j - increment];
					}
					else
					{
						break;
					}

				} 
				data[j] = temp;
			}

		}
	}
}

