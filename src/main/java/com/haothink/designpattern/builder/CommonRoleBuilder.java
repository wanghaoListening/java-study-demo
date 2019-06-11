package com.haothink.designpattern.builder;

/**
 * @author wanghao
 * @date 2019年06月11日 16:12
 * description: 普通角色建造者
 */
public class CommonRoleBuilder extends BaseBuilder{


    @Override
    public BaseBuilder buildHead() {
        role.setHead("common head");
        return this;
    }

    @Override
    public BaseBuilder buildFace() {
        role.setFace("common face");
        return this;
    }

    @Override
    public BaseBuilder buildBody() {
        role.setBody("common body");
        return this;
    }

    @Override
    public BaseBuilder buildHp() {
        role.setHp(100d);
        return this;
    }

    @Override
    public BaseBuilder buildSp() {
        role.setSp(100d);
        return this;
    }

    @Override
    public BaseBuilder buildMp() {
        role.setMp(100d);
        return this;
    }

    @Override
    public Role buildRole() {
        return role;
    }
}
