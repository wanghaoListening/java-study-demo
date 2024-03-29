package com.haothink.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wanghao on 2021/8/4
 **/
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PizzaStatus {

  ORDERED (5){
    @Override
    public boolean isOrdered() {
      return true;
    }
  },
  READY (2){
    @Override
    public boolean isReady() {
      return true;
    }
  },
  DELIVERED (0){
    @Override
    public boolean isDelivered() {
      return true;
    }
  };

  private int timeToDelivery;

  public boolean isOrdered() {return false;}

  public boolean isReady() {return false;}

  public boolean isDelivered(){return false;}

  @JsonProperty("timeToDelivery")
  public int getTimeToDelivery() {
    return timeToDelivery;
  }

  private PizzaStatus (int timeToDelivery) {
    this.timeToDelivery = timeToDelivery;
  }
}
