package com.haothink.guide;

/**
 * @author wanghao
 * @date 2019年05月20日 12:47
 * description:
 */
public class BitWiseExe {


    public static void main(String[] args) {

        int[] arr = {1,2,3,1,3,4,2,5,4};
        System.out.println(getOnceNumber(arr));
    }


    /**
     * 判断是否为偶数，采用位运算符
     * @param n
     * @return
     */
    private static boolean judgeEven(int n){

        return ((n & 1) == 0);
    }

    /**
     * 使用异或运算符
     * @param x
     * @param y
     * @return
     */
    private static void swapNumber(int x,int y){

        x = x^y;
        y = x^y;
        x = x^y;

        System.out.println("x="+x+"y="+y);
    }

    /**
     * 给你一组整型数据，这些数据中，其中有一个数只出现了一次，其他的数都出现了两次，让你来找出一个数 。
     * 两个相同的数异或的结果是 0，一个数和 0 异或的结果是它本身，所以我们把这一组整型全部异或一下，例
     * 如这组数据是：1，  2，  3，  4，  5，  1，  2，  3，  4。其中 5 只出现了一次，其他都出现了
     * 两次，把他们全部异或一下
     *
     * 时间复杂度为 O(n)，空间复杂度为 O(1)
     */
    private static int getOnceNumber(int[] arr){

        int tmp = arr[0];
        for(int i = 1;i < arr.length; i++){
            tmp = tmp ^ arr[i];
        }
        return tmp;
    }
}
