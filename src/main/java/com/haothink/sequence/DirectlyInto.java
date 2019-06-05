package com.haothink.sequence;

import java.util.Arrays;

/**
 * 直接插入排序
 * T(n)=O(n*n)
 * S(n)=O(1)
 * 此排序算法是稳定的：由于元素是从前向后比较，若当前要比较的元素与前面元素相同则会插到当前元素后面的空位
 * */
public class DirectlyInto {
	
	public static void main(String[] args) {
		int[] nums = new int[]{0,43,21,32,4,12,55};
		sorted(nums);
		System.out.println(Arrays.toString(nums));
	}

	public static int[] sorted(int[] nums){

		for(int i=2;i<=nums.length-1;i++){
			//先把要比较的元素放到监视哨当中
			nums[0] = nums[i];
			int j=i-1;
			//从后向前比较
			while(nums[0]<nums[j]){
					nums[j+1] = nums[j];
				j--;
			}
			//如果不小于前面的元素那就将监视哨元素放到前面的元素往后移动的空间
			nums[j+1] = nums[0];
		}
		return nums;

	}
}
