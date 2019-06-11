package com.haothink.designpattern.builder;

/**
 * @author wanghao
 * @date 2019年06月11日 16:24
 * description: 指挥者
 */
public class Director {

    public void construct(BaseBuilder builder){
        builder.buildBody().buildHead().buildFace().buildHp().buildMp().buildMp();

    }
}
