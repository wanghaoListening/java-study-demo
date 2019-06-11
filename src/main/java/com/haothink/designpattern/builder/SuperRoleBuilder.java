package com.haothink.designpattern.builder;

/**
 * @author wanghao
 * @date 2019年06月11日 16:21
 * description: 超级角色
 */
public class SuperRoleBuilder extends BaseBuilder {


    @Override
    public BaseBuilder buildHead() {
        role.setHead("suoer head");
        return this;
    }

    @Override
    public BaseBuilder buildFace() {
        role.setFace("super face");
        return this;
    }

    @Override
    public BaseBuilder buildBody() {
        role.setBody("super body");
        return this;
    }

    @Override
    public BaseBuilder buildHp() {
        role.setHp(120d);
        return this;
    }

    @Override
    public BaseBuilder buildSp() {
        role.setSp(120d);
        return this;
    }

    @Override
    public BaseBuilder buildMp() {
        role.setMp(120d);
        return this;
    }

    @Override
    public Role buildRole() {
        return role;
    }
}
