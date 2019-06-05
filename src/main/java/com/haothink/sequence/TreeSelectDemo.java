package com.haothink.sequence;

import java.util.Arrays;

/**
 *
 * 树形选择排序
 *
 * */
public class TreeSelectDemo {

	
	public static void main(String[] args) {
		Object[] obj = new Object[]{49,38,65,97,76,13,27,49,54,04};
		treeSelectSort(obj);
		System.out.println(Arrays.toString(obj));
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void treeSelectSort(Object[] a){  
		int len = a.length;
		int treeSize = 2 * len - 1;
		int low = 0;
		Object[] tree = new Object[treeSize];

		for(int i = len-1,j=0 ;i >= 0; --i,j++){
			tree[treeSize-1-j] = a[i];
		}

		for(int i = treeSize-1;i>0;i-=2){
			tree[(i-1)/2] = ((Comparable)tree[i-1]).compareTo(tree[i]) < 0 ? tree[i-1]:tree[i];
		}


		int minIndex;
		while(low < len){
			Object min = tree[0];

			a[low++] = min;
			minIndex = treeSize-1;


			while(((Comparable)tree[minIndex]).compareTo(min)!=0){
				minIndex--;
			}
			tree[minIndex] = Integer.MAX_VALUE;

			while(minIndex > 0){

				if(minIndex % 2 == 0){

					tree[(minIndex-1)/2] = ((Comparable)tree[minIndex-1]).compareTo(tree[minIndex])

							< 0 ? tree[minIndex-1]:tree[minIndex];
							minIndex = (minIndex-1)/2;
				}else{

					tree[minIndex/2] = ((Comparable)tree[minIndex]).compareTo(tree[minIndex+1])
							< 0 ? tree[minIndex]:tree[minIndex+1];
							minIndex = minIndex/2;
				}
			}

		}
	}


}

