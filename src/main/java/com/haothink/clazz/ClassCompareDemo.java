package com.haothink.clazz;


/**
 * 比较两个类是否相等
 * 两个类相等，需要类本身相等，并且使用同一个类加载器进行加载。这是因为每一个类加载器都拥有一个独立的类名称空间。
 * 这里的相等，包括类的 Class 对象的 equals() 方法、isAssignableFrom() 方法、isInstance() 方法的返回结果
 * 为 true，也包括使用 instanceof 关键字做对象所属关系判定结果为 true。
 */
public class ClassCompareDemo {


    public static void main(String[] args) {

        testIsAssignedFrom1();
        testIsAssignedFrom2();
        testIsAssignedFrom3();
        testInstanceOf1();
        testInstanceOf2();
    }

    /**
     * 判断一个类是否是另一个类的父类
     * 是打印true
     * 否打印false
     */
    public static void testIsAssignedFrom1() {
        System.out.println("String是Object的父类:"+String.class.isAssignableFrom(Object.class));
    }
    /**
     * 判断一个类是否是另一个类的父类
     * 是打印true
     * 否打印false
     */
    public static void testIsAssignedFrom2() {
        System.out.println("Object是String的父类:"+Object.class.isAssignableFrom(String.class));
    }
    /**
     * 判断一个类是否和另一个类相同
     * 是打印true
     * 否打印false
     */
    public static void testIsAssignedFrom3() {
        System.out.println("Object和Object相同:"+Object.class.isAssignableFrom(Object.class));
    }

    /**
     * 判断str是否是Object类的实例
     * 是打印true
     * 否打印false
     */
    public static void testInstanceOf1() {
        String str = new String();
        System.out.print("str是Object的实例:");
        System.out.println(str instanceof Object);
    }

    /**
     * 判断o是否是Object类的实例
     * 是打印true
     * 否打印false
     */
    public static void testInstanceOf2() {
        Object o = new Object();
        System.out.print("o是Object的实例:");
        System.out.println(o instanceof Object);
    }


}
