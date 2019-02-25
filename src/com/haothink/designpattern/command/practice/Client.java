package com.haothink.designpattern.command.practice;

/**
 * @author wanghao
 * @date 2019年02月18日 19:00
 * description:
 */
public class Client {

    public static void main(String[] args) {

        AbstractTelevation televation = new ConcreteTelevation();
        AbstractCommand closeCommand = new TVCloseCommand(televation);
        AbstractCommand openCommand = new TVOPenCommand(televation);
        AbstractController controller = new ConcreteController();
        controller.sendCommand(openCommand);
        controller.sendCommand(closeCommand);
    }
}
