package com.haothink.algorithm;

import com.googlecode.javaewah.EWAHCompressedBitmap;

import java.util.BitSet;

/**
 * @author wanghao
 * @date 2019年07月09日 14:00
 * description:
 */
public class BitMapDemo {


    public static void main(String[] args) {

        googleBitMap();
    }

    private static void jdkBitSet(){

        BitSet bits1 = new BitSet();
        BitSet bits2 = new BitSet();

        // set some bits
        for(int i=0; i<32; i++) {
            if((i%2) == 0) {bits1.set(i);}
            if((i%3) == 0) {bits2.set(i);}
        }

        System.out.println("初始的bits1: ");
        System.out.println(bits1);
        System.out.println("初始的bits2: ");
        System.out.println(bits2);

        //求交集
        bits1.and(bits2);
        System.out.println("bit1与bit2交集"+bits1);

        //求并集
        bits1.or(bits2);
        System.out.println("bit1与bit2并集"+bits1);

        //求差集,bit2不存在于bit1的位元素
        bits1.xor(bits2);
        System.out.println("bit1与bit2差集"+bits1);
    }


    private static void googleBitMap(){

        EWAHCompressedBitmap bits1 = new EWAHCompressedBitmap();
        EWAHCompressedBitmap bits2 = new EWAHCompressedBitmap();

        // set some bits
        for(int i=0; i<32; i++) {
            if((i%2) == 0) {bits1.set(i);}
            if((i%3) == 0) {bits2.set(i);}
        }

        System.out.println("初始的bits1: ");
        System.out.println(bits1);
        System.out.println("初始的bits2: ");
        System.out.println(bits2);

        //求交集
        EWAHCompressedBitmap andBitMap = bits1.and(bits2);
        System.out.println("bit1与bit2交集"+andBitMap);

        //求并集
        EWAHCompressedBitmap orBitMap = bits1.or(bits2);
        System.out.println("bit1与bit2并集"+orBitMap);

        //求差集,不同时存在于两个集合的位元素
        EWAHCompressedBitmap xorBitMap = bits1.xor(bits2);
        System.out.println("bit1与bit2差集"+xorBitMap);
    }



}
