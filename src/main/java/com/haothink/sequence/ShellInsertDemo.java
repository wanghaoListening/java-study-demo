package com.haothink.sequence;

import java.util.Arrays;

/**
 * 希尔排序：
 * 直接插入排序在在待排序的关键字序列基本有序且关键字个数n较少时，
 * 其算法性能最佳，
 * 希尔排序又称缩小总量排序法，是一种基于插入排序的思想，利用了直接
 * 插入排序的最佳性质，首先将待排序的关键字序列分成若干小的子序列，对
 * 子序列进行直接的插入排序，使整个待排序的序列排好序。较直接插入排序法
 * 的性能有较大改进，
 * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
 * 待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序
 * 时间复杂度：O（n^2）
 * 不稳定排序
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
		//每次将步长缩短为原来的一半
		for (int increment = data.length / 2; increment > 0; increment /= 2)
		{
			for (int i = increment; i < data.length; i++) 
			{
				temp = data[i];
				for (j = i; j >= increment; j -= increment) 
				{
					if(temp < data[j - increment])//如想从小到大排只需修改这里
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

