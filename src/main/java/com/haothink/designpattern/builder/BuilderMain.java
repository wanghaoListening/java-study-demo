package com.haothink.designpattern.builder;

/**
 * @author wanghao
 * @date 2019年06月11日 16:28
 * description:
 * Builder模式的定义是“将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 * ”，它属于创建类模式，一般来说，如果一个对象的构建比较复杂，超出了构造函数所能包含的范围，就可以
 * 使用工厂模式和Builder模式，相对于工厂模式会产出一个完整的产品，Builder应用于更加复杂的对象的
 * 构建，甚至只会构建产品的一个部分。
 */
public class BuilderMain {

    public static void main(String[] args) {

        Director director = new Director();
        BaseBuilder commonBuilder = new CommonRoleBuilder();

        director.construct(commonBuilder);
        Role commonRole = commonBuilder.buildRole();
        System.out.println(commonRole);
    }
}
