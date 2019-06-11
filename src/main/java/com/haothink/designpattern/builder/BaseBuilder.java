package com.haothink.designpattern.builder;

/**
 * @author wanghao
 * @date 2019年06月11日 16:11
 * description: 抽象建造者：BaseBuilder
 */
public abstract class BaseBuilder {

    protected Role role = new Role();

    public abstract BaseBuilder buildHead();

    public abstract BaseBuilder buildFace();

    public abstract BaseBuilder buildBody();

    public abstract BaseBuilder buildHp();

    public abstract BaseBuilder buildSp();

    public abstract BaseBuilder buildMp();

    public Role buildRole() {
        return role;
    }
}
