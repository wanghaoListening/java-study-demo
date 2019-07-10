package com.haothink.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author wanghao
 * @date 2019年07月10日 11:01
 * description: Java中的四种引用
 *
 * 1. new一个对象的引用就属于强引用
 * 2. 软引用会在内存不够的时候被垃圾回收
 * 3. 弱引用只能存活到下一次垃圾回收之前
 * 4. 为一个对象设置虚引用的唯一目的是能在这个对象被回收时收到一个系统通知。
 * 一个对象是否有虚引用的存在，不会对其生存时间造成影响，也无法通过虚引用得到一个对象。
 */
public class JavaReferenceDemo {


    public static void main(String[] args) {

        //强引用
        Object strongReference = new Object();

        //软引用
        Object softReference = new Object();
        SoftReference<Object> sf = new SoftReference<>(softReference);

        //弱引用
        Object weakReference = new Object();
        WeakReference<Object> wf = new WeakReference<>(weakReference);

        //虚引用
        Object phantomReference = new Object();
        PhantomReference<Object> pf = new PhantomReference<>(phantomReference, null);
    }
}
