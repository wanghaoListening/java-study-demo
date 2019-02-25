package com.haothink.designpattern.command.define;

/**
 * @author wanghao
 * @date 2019年02月18日 16:53
 * description:
 */
public class Invoker {

    private AbstractCommand command;


    public Invoker(AbstractCommand command) {
        this.command = command;
    }

    public Invoker() {
    }

    public void setCommand(AbstractCommand command) {
        this.command = command;
    }

    /**
     * 业务方法，用于调用命令类的execute()方法
     */
    public void call() {
        command.execute();
    }
}
