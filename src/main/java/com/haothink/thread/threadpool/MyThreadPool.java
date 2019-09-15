package com.haothink.thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wanghao
 * @date 2019年09月15日 19:34
 * description: 模拟线程池
 */
public class MyThreadPool {

    private int coreSize;
    private int maxSize;
    private static final int DEFAULT_TASK_SIZE = 10;
    private BlockingQueue<Runnable> blockingQueue;
    private List<Thread> threads;

    public MyThreadPool(int coreSize, int maxSize) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        blockingQueue = new LinkedBlockingQueue<>();

        threads = new ArrayList<>(this.maxSize);

        for(int i=0;i<coreSize;i++){

            Thread thread = new MyWorker(blockingQueue);
            threads.add(thread);
            thread.start();
        }
    }

    public void submit(Runnable r){

        if(null == blockingQueue){
            throw new RuntimeException("阻塞队列初始化异常");
        }
        blockingQueue.offer(r);
    }


    private static class MyWorker extends Thread{

        private BlockingQueue<Runnable> blockingQueue;

        public MyWorker(BlockingQueue<Runnable> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            super.run();
            for(;;){
                try {
                    //没有任务线程会阻塞在这里
                    Runnable runnable = blockingQueue.take();
                    runnable.run();
                } catch (InterruptedException e) {
                    System.out.println("occur exception: "+e);
                }
            }
        }
    }
}
