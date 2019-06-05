package com.haothink.designpattern.observer.jdkobserver.copy;

import java.util.Observable;
import java.util.Observer;

/**
 * @author wanghao
 * @date 2019年06月05日 15:40
 * description:
 */
public class ConcreteObserver implements Observer{
    private String name;
    @Override
    public void update(Observable o, Object arg) {

        System.out.println(name+"----"+((WeatherSubject)o).getContent());
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
