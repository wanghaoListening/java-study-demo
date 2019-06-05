package com.haothink.zookeeper.lock;

/**
 * @author wanghao
 * @date 2019年05月30日 11:36
 * description: 分布式锁接口
 */
public interface DistributedLock {

    /**
     * 加锁
     * @throws Exception
     *         加锁异常
     */
    void lock() throws Exception;

    /**
     * 获取锁
     * @return 成功:True 失败:False
     * @throws Exception
     *         获取锁异常
     */
    boolean tryLock() throws Exception;

    /**
     * 获取锁在指定的时间
     * @param millisecond
     *        在指定的时间获取锁
     * @return
     *        成功:True 失败:False
     * @throws Exception
     *        获取锁异常
     */
    boolean tryLock(long millisecond) throws  Exception;

    /**
     * 释放锁
     * @throws Exception
     * 释放锁异常
     */
    void unLock() throws Exception;


}
