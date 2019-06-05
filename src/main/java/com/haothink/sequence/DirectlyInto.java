package com.haothink.sequence;

import java.util.Arrays;

/**
 * T(n)=O(n*n)
 * S(n)=O(1)
 * */
public class DirectlyInto {
	
	public static void main(String[] args) {
		int[] nums = new int[]{0,43,21,32,4,12,55};
		sorted(nums);
		System.out.println(Arrays.toString(nums));
	}

	public static int[] sorted(int[] nums){

		for(int i=2;i<=nums.length-1;i++){

			nums[0] = nums[i];
			int j=i-1;

			while(nums[0]<nums[j]){
					nums[j+1] = nums[j];
				j--;
			}

			nums[j+1] = nums[0];
		}
		return nums;

	}
}
