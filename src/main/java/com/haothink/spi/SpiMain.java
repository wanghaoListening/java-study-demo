package com.haothink.spi;

import com.haothink.spi.java.IRepository;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author wanghao
 * @date 2019年06月09日 19:40
 * description:
 */
public class SpiMain {

    public static void main(String[] args) {

        ServiceLoader<IRepository> serviceLoader = ServiceLoader.load(IRepository.class);
        Iterator<IRepository> it = serviceLoader.iterator();
        while (it.hasNext()){
            IRepository demoService = it.next();
            System.out.println("class:" + demoService.getClass().getName());
            demoService.save("tom");
        }
    }
}
