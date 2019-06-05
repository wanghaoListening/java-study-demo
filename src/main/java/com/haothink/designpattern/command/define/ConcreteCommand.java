package com.haothink.designpattern.command.define;

/**
 * @author wanghao
 * @date 2019年02月18日 16:56
 * description:
 */
public  class ConcreteCommand extends AbstractCommand {
    private AbstractReceiver receiver;

    public ConcreteCommand(AbstractReceiver receiver) {
        super();
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();//调用请求接收者的业务处理方法action()
    }
}

