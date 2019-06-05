package com.haothink.designpattern.command.practice;

/**
 * @author wanghao
 * @date 2019年02月18日 17:56
 * description:
 */
public class TVCloseCommand extends AbstractCommand{

    public TVCloseCommand(AbstractTelevation televation){
        this.televation = televation;
    }

    @Override
    public void execute() {
        televation.close();
    }
}
