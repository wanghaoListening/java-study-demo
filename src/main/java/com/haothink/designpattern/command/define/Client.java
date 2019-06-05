package com.haothink.designpattern.command.define;

/**
 * @author wanghao
 * @date 2019年02月18日 17:03
 * description:
 */
public class Client {

    public static void main(String[] args) {
        AbstractReceiver receiver = new ConcreteReceiver();
        AbstractCommand command = new ConcreteCommand(receiver);

        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.call();
    }
}
