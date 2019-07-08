package com.haothink.thread.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * @author wanghao
 * @date 2019年07月07日 15:17
 * description:
 */
public class ForkJoinMain {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinExample example = new ForkJoinExample(1, 10000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future result = forkJoinPool.submit(example);
        System.out.println(result.get());
    }
}
