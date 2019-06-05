package com.haothink.sequence;

import java.util.Arrays;

/**
 * 折半插入排序
 * 前提：对已经排好序的序列进行折半查找
 * 算法分析：采用折半查找排序，可以减少关键字的比较次数，
 * 每插入一个元素，需要比较的次数最大为折半判定树的深度，
 * 如插入第i个元素时，需进行log2i次比较，因此插入n-1
 * 个元素的平均关键字比较次序为nlog2n，当n较大时折半
 * 插入排序的比较次序比直接插入排序的最坏情况要好很多，但
 * 比起最好情况要差。
 * 3.1 空间复杂度：如上代码，使用了一个辅助单元key，空间复杂度为O(1)
 * 3.2 时间复杂度：虽然折半查找减少了记录比较次数，但没有减少移动次数，因此时间复杂度同直接查找算法。
   3.2.1 最好情况：时间复杂度O(n)(插入到序列最后)
   3.2.2 最坏情况：时间复杂度O(n^2)
   3.2.3 平均时间复杂度：O(n^2)
   3.3 稳定性：稳定(原序列元素的位置相对不变)
 * 
 * */
public class BinaryInsertion {

	public static void main(String[] args) {
		int[] nums = new int[]{12,15,23,33,45,0};
		int ele = 13;
		int[] newnums = binaryInsert(nums, ele);

		System.out.println(Arrays.toString(newnums));
	}
	//折半插入排序
	//先查找完再移动
	public static int[] binaryInsert(int[] nums,int ele){
		int i,low,high,mid=0;
		low=0;
		high=nums.length-1;
		//先寻找插入点，最终插入点在low
		while(low<=high){
			mid=(low+high)/2;
			if(ele<nums[mid])
				high=mid-1;
			else 
				low=mid+1;
		}
		//记录依次向后移动
		for(i=nums.length-1;i>low;i--)
			nums[i] = nums[i-1];
		nums[mid] = ele;

		return nums;
	}

}

