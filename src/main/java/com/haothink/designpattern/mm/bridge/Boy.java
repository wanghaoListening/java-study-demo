package com.haothink.designpattern.mm.bridge;

/**
 * @author wanghao
 * @date 2019年06月05日 15:43
 * description:
 */

public class Boy {

    private String name;

    public void pursue(MM mm){
        Gift gift = new Ordinary(new Phone());
        boolean b = mm.getGift(gift);
        System.out.println(b);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}