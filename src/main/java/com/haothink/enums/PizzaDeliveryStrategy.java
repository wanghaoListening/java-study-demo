package com.haothink.enums;

/**
 * Created by wanghao on 2021/8/4
 **/
public enum PizzaDeliveryStrategy {

  EXPRESS {
    @Override
    public void deliver(Pizza pz) {
      System.out.println("Pizza will be delivered in express mode");
    }
  },
  NORMAL {
    @Override
    public void deliver(Pizza pz) {
      System.out.println("Pizza will be delivered in normal mode");
    }
  };

  public abstract void deliver(Pizza pz);
}
