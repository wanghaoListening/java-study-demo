package com.haothink.zookeeper.lock;

import com.haothink.zookeeper.CuratorLock;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.TimeUnit;

/**
 * @author wanghao
 * @date 2019年08月21日 10:13
 * description:
 */
public class CuratorLockImpl implements CuratorLock {


    // session过期时间
    private static int SESSION_TIMEOUT = 3000;
    // 连接超时时间
    private static int CONNECTION_TIMEOUT = 3000;

    // 锁节点
    private static final String CURATOR_LOCK = "/distribute-lock";

    private static final String ZK_ADDRESS = "dev1.zk1.zhichengcredit.com:2181,dev1.zk2.zhichengcredit.com:2181,dev1.zk3.zhichengcredit.com:2181";

    InterProcessMutex mutex = null;

    {
        CuratorFramework framework = CuratorFrameworkFactory
                .newClient(ZK_ADDRESS,
                SESSION_TIMEOUT, CONNECTION_TIMEOUT,
                new RetryNTimes(3,5000));
        framework.start();
        mutex = new InterProcessMutex(framework, CURATOR_LOCK);
        System.out.println("init lock success "+mutex);
    }
    @Override
    public boolean tryLock() {

        if(null == mutex){
            throw new RuntimeException("初始化锁实例失败");
        }
        try {

            return mutex.acquire(2, TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        return false;
    }

    @Override
    public void releaseLock() {

        if(null != mutex){
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final CuratorLock curatorLock = new CuratorLockImpl();


        int count =1;
        final Task task = new Task(count,curatorLock);

        for(int i=0;i<6;i++){

            new Thread(task).start();
        }
    }

    private static class Task implements Runnable{

        private int count = 1;

        CuratorLock curatorLock;

        public Task(int count,CuratorLock curatorLock){
            this.count = count;
            this.curatorLock = curatorLock;
        }

        @Override
        public void run() {

            if(curatorLock.tryLock()){
                System.out.println(count++);
                curatorLock.releaseLock();
            }
        }
    }
}
