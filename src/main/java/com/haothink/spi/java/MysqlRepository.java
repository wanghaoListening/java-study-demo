package com.haothink.spi.java;

/**
 * @author wanghao
 * @date 2019年06月09日 22:00
 * description:
 */
public class MysqlRepository implements IRepository {

    @Override
    public void save(String data) {
        System.out.println("Save " + data + " to Mysql");
    }
}
