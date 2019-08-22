package com.haothink.zookeeper;

import java.util.concurrent.TimeUnit;

/**
 * @author wanghao
 * @date 2019年08月21日 10:12
 * description:
 */
public interface CuratorLock {


    public boolean tryLock();
    public boolean tryLock(long time, TimeUnit unit);

    public void releaseLock();
}
