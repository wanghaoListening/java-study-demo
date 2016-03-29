package com.haothink.sequence;

import java.util.Arrays;

/**
 * 树形选择排序
 * 在简单的选择排序中，首先从n个记录选择关键字最小的记录需要n-1
 * 次比较，在n-1个记录中选择最小的需要n-2次比较结果，每次都没有
 * 利用上次的比较结果，所以比较操作的时间复杂度为O(n^2)，欲将低
 * 比较次数则需要把比较过程的大小关系保存下来。
 * 
 * 树形选择排序也称为锦标赛排序，其基本思想是将需要比较的n个元素两两
 * 进行比较选出比较小的元素，然后再对比较后的n/2个元素进行两两比较选出
 * 较小的元素，反复此动作直到选出最小的元素，此过程可以用一颗满二叉树表示
 * 不满时用关键字为null的节点补满，选出的最小元素就是这棵树的根节点，然后
 * 将最小关键字所对应的叶子节点的关键子用null来代替，然后从改叶节点开始
 * 和其兄弟节点进行比较，修改该叶子节点到根节点各路径上各节点的值。则根节点
 * 的值，即为最小值。
 * 
 * 算法分析
 * 假设排序所用满二叉树的深度为h。在树形选择排序中，除了最小关键字，被选出的其它较小的关键字
 * 都走了一条由叶子节点到根节点的比较的过程，且均需比较h-1次可以证明含有n个叶子节点的完全
 * 二叉树的深度h=[log2n]+1,因此在树形选择排序中，每选出一个较小的关键字需要进行[log2n]
 * 次比较，所以其时间复杂度为O(nlog2n)移动次数不超过比较次数，故总的算法时间复杂度为O(nlog2n)
 * 与简单排序相比降低了比较次数的数量级，但是增加了n-1个额外的存储空间存放中间的比较结果。
 * 
 * 不稳定排序
 * */
public class TreeSelectDemo {

	
	public static void main(String[] args) {
		Object[] obj = new Object[]{49,38,65,97,76,13,27,49,54,04};
		treeSelectSort(obj);
		System.out.println("排序后为"+Arrays.toString(obj));
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void treeSelectSort(Object[] a){  
		int len = a.length;
		int treeSize = 2 * len - 1; //完全二叉树的节点数
		int low = 0;
		Object[] tree = new Object[treeSize];  //临时的树存储空间
		//由后向前填充此树，索引从0开始
		for(int i = len-1,j=0 ;i >= 0; --i,j++){  //填充叶子节点
			tree[treeSize-1-j] = a[i];
		}

		for(int i = treeSize-1;i>0;i-=2){ //填充非终端节点
			tree[(i-1)/2] = ((Comparable)tree[i-1]).compareTo(tree[i]) < 0 ? tree[i-1]:tree[i];
		}

		//不断移走最小节点
		int minIndex;
		while(low < len){
			Object min = tree[0];
			//最小值
			a[low++] = min;
			minIndex = treeSize-1;

			//找到最小值的索引
			while(((Comparable)tree[minIndex]).compareTo(min)!=0){
				minIndex--;
			}
			tree[minIndex] = Integer.MAX_VALUE;
			//设置一个最大值标志
			//找到其兄弟节点
			while(minIndex > 0){
				//如果其还有父节点
				if(minIndex % 2 == 0){
					//如果是右节点
					tree[(minIndex-1)/2] = ((Comparable)tree[minIndex-1]).compareTo(tree[minIndex])

							< 0 ? tree[minIndex-1]:tree[minIndex];
							minIndex = (minIndex-1)/2;
				}else{
					//如果是左节点
					tree[minIndex/2] = ((Comparable)tree[minIndex]).compareTo(tree[minIndex+1])
							< 0 ? tree[minIndex]:tree[minIndex+1];
							minIndex = minIndex/2;
				}
			}

		}
	}


}

