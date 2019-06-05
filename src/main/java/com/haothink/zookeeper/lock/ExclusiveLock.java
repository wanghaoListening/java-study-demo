package com.haothink.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wanghao
 * @date 2019年05月30日 14:30
 * description: zookeeper实现独占锁
 */
public class ExclusiveLock implements DistributedLock {

    private static final Logger logger = LoggerFactory.getLogger(ExclusiveLock.class);

    private static final String LOCK_NODE_FULL_PATH = "/exclusive_lock/lock";

    private static final String LOCK_ROOT_PATH = "/exclusive_lock";

    /** 自旋测试超时阈值，考虑到网络的延时性，这里设为1000毫秒 */
    private static final long SPIN_FOR_TIMEOUT_THRESHOLD = 1000L;

    private static final long SLEEP_TIME = 100L;

    private ZooKeeper zooKeeper;

    private CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private CyclicBarrier lockBarrier = new CyclicBarrier(2);

    private LockStatus lockStatus;

    private String id = String.valueOf(new Random(System.nanoTime()).nextInt(10000000));

    private String host;
    private int port;


    public ExclusiveLock(String host,int port) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(host+":"+port, 1000, new LockNodeWatcher());
        lockStatus = LockStatus.UNLOCK;
        connectedSemaphore.await();
    }

    @Override
    public void lock() throws Exception {

        if(LockStatus.UNLOCK != lockStatus){
            return;
        }
        // 1. 创建锁节点ƒ
        if(createLockNode()){
            System.out.println("[" + id  + "]" + " 获取锁");
            lockStatus = LockStatus.LOCKED;
            return;
        }
        lockStatus = LockStatus.TRY_LOCK;
        lockBarrier.await();
    }

    @Override
    public boolean tryLock() throws Exception {

        if(LockStatus.LOCKED == lockStatus){
            return true;
        }
        Boolean created = createLockNode();
        lockStatus = created ? LockStatus.LOCKED : LockStatus.UNLOCK;
        return created;
    }

    @Override
    public boolean tryLock(long millisecond) throws Exception {
        long millisTimeout = millisecond;
        if (millisTimeout <= 0L) {
            return false;
        }

        final long deadline = System.currentTimeMillis() + millisTimeout;
        for(;;){
            if(tryLock()){
                return true;
            }

            if (millisTimeout > SPIN_FOR_TIMEOUT_THRESHOLD) {
                Thread.sleep(SLEEP_TIME);
            }

            millisTimeout = deadline - System.currentTimeMillis();
            if (millisTimeout <= 0L) {
                return false;
            }

        }
    }

    @Override
    public void unLock() throws Exception {

        if (lockStatus == LockStatus.UNLOCK) {
            return;
        }

        deleteLockNode();
        lockStatus = LockStatus.UNLOCK;
        lockBarrier.reset();
        System.out.println("[" + id  + "]" + " 释放锁");

    }

    private Boolean createLockNode(){

        try {
            zooKeeper.create(LOCK_NODE_FULL_PATH,"".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            return true;
        }catch (Exception e){
            System.out.println("[" + id  + "]"+"创建节点失败："+e.toString());
            return false;
        }
    }

    private void deleteLockNode() throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(LOCK_NODE_FULL_PATH, false);
        zooKeeper.delete(LOCK_NODE_FULL_PATH, stat.getVersion());
    }

    private void ensureRootPath() throws InterruptedException {
        try {
            if (zooKeeper.exists(LOCK_ROOT_PATH,true)==null){
                zooKeeper.create(LOCK_ROOT_PATH,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }


    enum LockStatus {
        TRY_LOCK,
        LOCKED,
        UNLOCK
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }


    class LockNodeWatcher implements Watcher{

        @Override
        public void process(WatchedEvent watchedEvent) {
            if (Event.KeeperState.SyncConnected != watchedEvent.getState()) {
                return;
            }
            // 2. 设置监视器
            try {
                zooKeeper.exists(LOCK_NODE_FULL_PATH, this);
            } catch (KeeperException | InterruptedException e) {
                System.out.println("watcher process occur ex:"+e);
            }

            if (Event.EventType.None == watchedEvent.getType() && watchedEvent.getPath() == null) {
                connectedSemaphore.countDown();
            } else if (Event.EventType.NodeDeleted == watchedEvent.getType()
                    && watchedEvent.getPath().equals(LOCK_NODE_FULL_PATH)) {

                // 3. 再次尝试创建锁及诶单
                if (lockStatus == LockStatus.TRY_LOCK && createLockNode()) {
                    lockStatus = LockStatus.LOCKED;
                    try {
                        lockBarrier.await();
                        System.out.println("[" + id  + "]" + " 获取锁");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
