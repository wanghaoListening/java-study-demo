package com.haothink.thread.concurrent.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author wanghao
 * @date 2019年07月08日 17:07
 * description: cas 中的ABA问题
 */

public class ABAProblemDemo{

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<>(1, 0);

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        repairABA();
    }

    private static void testABAProblem(){

        Runnable main = () -> {
            //定义变量 atomicInteger = 1
            System.out.println("操作线程" + Thread.currentThread() +",初始值 = " + atomicInteger);
            try {
                //等待1秒 ，以便让干扰线程执行
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // CAS操作
            boolean isCASSuccess = atomicInteger.compareAndSet(1,2);
            System.out.println("操作线程" + Thread.currentThread() +",CAS操作结果: " + isCASSuccess);
        };

        Runnable other = () -> {
            try {
                //保证让main线程先执行操作
                Thread.sleep(100);
                atomicInteger.incrementAndGet(); // a 加 1, a + 1 = 1 + 1 = 2
                System.out.println("操作线程" + Thread.currentThread() +",【increment】 ,值 = "+ atomicInteger);
                atomicInteger.decrementAndGet(); // a 减 1, a - 1 = 2 - 1 = 1
                System.out.println("操作线程" + Thread.currentThread() +",【decrement】 ,值 = "+ atomicInteger);
            }catch (Exception e){
                e.printStackTrace();
            }

        };

        executorService.submit(main);
        executorService.submit(other);

    }


    private static void repairABA(){

        Runnable main = () -> {
            //定义变量 a = 1
            System.out.println("操作线程" + Thread.currentThread() +",初始值 = " + atomicStampedRef.getReference());
            //获取当前标识别
            int stamp = atomicStampedRef.getStamp();
            try {
                //等待1秒 ，以便让干扰线程执行
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // CAS操作
            boolean isCASSuccess = atomicStampedRef.compareAndSet(1,2,stamp,stamp +1);
            System.out.println("操作线程" + Thread.currentThread() +",CAS操作结果: " + isCASSuccess);
        };

        Runnable other = () -> {
            try {
                //保证让main线程先执行操作
                atomicStampedRef.compareAndSet(1,2,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
                System.out.println("操作线程" + Thread.currentThread() +",【increment】 ,值 = "+ atomicStampedRef.getReference());
                atomicStampedRef.compareAndSet(2,1,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
                System.out.println("操作线程" + Thread.currentThread() +",【decrement】 ,值 = "+ atomicStampedRef.getReference());
            }catch (Exception e){
                e.printStackTrace();
            }

        };

        executorService.submit(main);
        executorService.submit(other);
    }
}
