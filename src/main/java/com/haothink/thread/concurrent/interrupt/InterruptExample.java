package com.haothink.thread.concurrent.interrupt;

/**
 * @author wanghao
 * @date 2019年07月06日 16:14
 * description:
 *
 * 通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，
 * 那么就会抛出 InterruptedException，从而提前结束该线程。但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
 *
 * 对于以下代码，在 main() 中启动一个线程之后再中断它，由于线程中调用了 Thread.sleep() 方法，
 * 因此会抛出一个 InterruptedException，从而提前结束线程，不执行之后的语句。
 *java.lang.InterruptedException: sleep interrupted
 * 	at java.lang.Thread.sleep(Native Method)
 * 	at com.haothink.thread.concurrent.interrupt.InterruptExample$MyThread1.run(InterruptExample.java:14)
 *
 */
public class InterruptExample {

    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new MyThread1();
        thread1.start();
        thread1.interrupt();
        System.out.println("Main run");
    }
}
