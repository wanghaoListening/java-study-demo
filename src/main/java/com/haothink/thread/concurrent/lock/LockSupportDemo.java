package com.haothink.thread.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wanghao
 * @date 2019年07月06日 15:33
 * description:
 */
public class LockSupportDemo {

    public static void main(String[] args) {

        Thread mazdaCar = Thread.currentThread();

        new Thread(new TeslaCar(mazdaCar)).start();
        System.out.println("waiting tesla cross the road");
        LockSupport.park();
        System.out.println("Mazda cross the road successful");
    }
}


class TeslaCar implements Runnable{

    private Thread t;

    public TeslaCar(Thread t) {
        this.t = t;
    }

    @Override
    public void run() {
        try {
            System.out.println("Tesla is waiting 1000s");
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tesla cross the road successful");
        LockSupport.unpark(t);
    }
}
