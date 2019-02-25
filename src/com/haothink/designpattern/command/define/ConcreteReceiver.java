package com.haothink.designpattern.command.define;

/**
 * @author wanghao
 * @date 2019年02月18日 17:01
 * description:
 */
public class ConcreteReceiver extends AbstractReceiver{

    @Override
    public void action() {
        System.out.println("ConcreteReceiver receives the command!");
    }

}

