package com.haothink.designpattern.command.practice;

/**
 * @author wanghao
 * @date 2019年02月18日 18:45
 * description:
 */
public class ConcreteTelevation extends AbstractTelevation{
    @Override
    public void off() {
        System.out.println("开启电视机....");
    }

    @Override
    public void close() {
        System.out.println("关闭电视机....");
    }
}
