package com.haothink.designpattern.command.practice;

/**
 * @author wanghao
 * @date 2019年02月18日 18:45
 * description:
 */
public class ConcreteController extends AbstractController{

    public ConcreteController(){

    }

    @Override
    public void sendCommand(AbstractCommand command) {
        this.command = command;
        command.execute();
    }
}
