package com.haothink.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wanghao
 * @date 2019年06月07日 16:01
 * description: zookeeper分布式锁，
 */
public class ZooKeeperSession {


    private static final long ONE_SECONDS = 1000;

    private static final long Three_SECONDS = 3000;

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private ZooKeeper zooKeeper;

    private CountDownLatch latch;

    public ZooKeeperSession(){

        try {
            this.zooKeeper = new ZooKeeper("127.0.0.1:2181",500,new ZookeeperWatcher());
            connectedSemaphore.await();
            System.out.println("zookeeper session connected");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public boolean acquireLock(Long bizId){

        if(Objects.isNull(bizId)){
            throw new RuntimeException("the bizId must be not null");
        }

        String path = "/biz-lock-" + bizId;

        try {
            //创建一个临时节点，赋予该节点的权限为默认匿名权限
            zooKeeper.create(path,"".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            return true;
        }catch (Exception e){

            for(;;){

                // 相当于是给node注册一个监听器，去看看这个监听器是否存在
                try {
                    Stat stat = zooKeeper.exists(path, true);
                    if(Objects.nonNull(stat)){
                        latch = new CountDownLatch(1);
                        latch.await(ONE_SECONDS, TimeUnit.MILLISECONDS);
                        latch = null;
                    }
                    zooKeeper.create(path,"".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    return true;
                } catch (Exception ex) {
                    System.out.println("watch or create zNode fail");
                }
            }
        }

    }


    public void releaseLock(Long bizId){
        if(Objects.isNull(bizId)){
            throw new RuntimeException("the bizId must be not null");
        }
        String path = "/biz-lock-" + bizId;

        try {
            zooKeeper.delete(path,-1);
            System.out.println("release the lock for bizId[id=" + bizId + "]......");
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * 对znode的状态进行监听
     */
    private class ZookeeperWatcher implements Watcher{

        @Override
        public void process(WatchedEvent event) {
            System.out.println("receive watch event: "+event.getState());
            if(Event.KeeperState.SyncConnected == event.getState()){
                System.out.println("acquire lock success, please do biz");
                connectedSemaphore.countDown();
            }

            if(Objects.nonNull(latch)){
                latch.countDown();
            }
        }
    }

    private static class Singleton {

        private static ZooKeeperSession instance;

        static {
            instance = new ZooKeeperSession();
        }

        public static ZooKeeperSession getInstance() {
            return instance;
        }

    }

    /**
     * 获取单例
     *
     * @return
     */
    public static ZooKeeperSession getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 初始化单例的便捷方法
     */
    public static void init() {
        getInstance();
    }
}
