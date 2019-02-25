package com.haothink.designpattern.command.practice;

/**
 * @author wanghao
 * @date 2019年02月18日 17:55
 * description:
 */
public class TVOPenCommand extends AbstractCommand{

    public TVOPenCommand(AbstractTelevation televation){
        this.televation = televation;
    }

    @Override
    public void execute() {
        this.televation.off();
    }
}
