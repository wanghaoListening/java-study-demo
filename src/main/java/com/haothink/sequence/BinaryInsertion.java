package com.haothink.sequence;

import java.util.Arrays;

/**
 *
 * 折半插入排序
 * 
 * */
public class BinaryInsertion {

	public static void main(String[] args) {
		int[] nums = new int[]{12,15,23,33,45,0};
		int ele = 13;
		int[] newnums = binaryInsert(nums, ele);

		System.out.println(Arrays.toString(newnums));
	}

	public static int[] binaryInsert(int[] nums,int ele){
		int i,low,high,mid=0;
		low=0;
		high=nums.length-1;

		while(low<=high){
			mid=(low+high)/2;
			if(ele<nums[mid])
				high=mid-1;
			else 
				low=mid+1;
		}

		for(i=nums.length-1;i>low;i--)
			nums[i] = nums[i-1];
		nums[mid] = ele;

		return nums;
	}

}

