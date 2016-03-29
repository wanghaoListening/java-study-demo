package com.haothink.sequence;

import java.util.Arrays;

/**
 * 快速排序(快速排序中的快是指一次交换可能消除多个逆序)
 * 此算法是不稳定排序
 * 算法思想:先从序列选出一个记录key，然后将序列中大于key的元素移到后头，小于key的元素移到
 * key的前头，此后将key插入两个字表的中间，至此成为一趟排序，然后再对字表进行重复动作。直到字表
 * 长度不超过一为止。
 * 时间复杂度，最好，O(nlog2n) 最坏为，O(n^2)
 * 空间复杂度，O(log2n)
 * 快排空间复杂度分析，快排的递归算法执行过程对应一颗二叉树，理想情况下是一颗完全二叉树
 * 递归工作站的大小与递归调用二叉树的深度对应，平均情况下辅助空间的，复杂度为O(log2n)
 * */
public class QKSortDemo {
	
	public static void main(String[] args) {
		int[] data = new int[]{49,38,65,97,76,13,27,49,54,04};
		quick(data);
		System.out.println("排序后为"+Arrays.toString(data));
	}
	
	public static void quick(int[] numbers)
	{
		if(numbers.length > 0)   //查看数组是否为空
		{
			quickSort(numbers, 0, numbers.length-1);
		}
	}
	
	public static void quickSort(int[] numbers,int low,int high)
	{
		if(low < high)
		{
			int middle = getMiddle(numbers,low,high); //将numbers数组进行一分为二
			quickSort(numbers, low, middle-1);   //对低字段表进行递归排序
			quickSort(numbers, middle+1, high); //对高字段表进行递归排序
		}

	}
	public static int getMiddle(int[] numbers, int low,int high)
	{
		int temp = numbers[low]; //数组的第一个作为中轴
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
		numbers[low] = temp ; //中轴记录到尾
		return low ; // 返回中轴的位置
	}
	
	
}
