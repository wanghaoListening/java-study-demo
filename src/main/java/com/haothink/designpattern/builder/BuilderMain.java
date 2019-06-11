package com.haothink.designpattern.builder;

/**
 * @author wanghao
 * @date 2019年06月11日 16:28
 * description:
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
