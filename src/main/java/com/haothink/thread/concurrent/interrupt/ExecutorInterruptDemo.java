package com.haothink.thread.concurrent.interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wanghao
 * @date 2019年07月06日 16:16
 * description:
 *
 *调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，
 * 则相当于调用每个线程的 interrupt() 方法。
 *
 * 如果只想中断 Executor 中的一个线程，可以通过使用 submit() 方法来提交一个线程，它会返回一个 Future<?> 对象，
 * 通过调用该对象的 cancel(true) 方法就可以中断线程。
 */
public class ExecutorInterruptDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdownNow();
        System.out.println("Main run");
    }
}
