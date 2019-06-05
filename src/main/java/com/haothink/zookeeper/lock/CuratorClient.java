package com.haothink.zookeeper.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author wanghao
 * @date 2019年05月29日 20:01
 * description: Curator对zkClient进行了封装用它获取锁更方便
 */
public class CuratorClient {


    public static void main(String[] args) throws Exception {

        //创建zookeeper的客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //本地搭建的单节点zk
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);

        client.start();
        //创建分布式锁, 锁空间的根节点路径为/curator/lock
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");

        //获取锁
        mutex.acquire();
        //执行相应的业务流程
        System.out.println("Enter mutex");
        //完成业务流程, 释放锁
        mutex.release();
        //关闭客户端
        client.close();
    }
}
