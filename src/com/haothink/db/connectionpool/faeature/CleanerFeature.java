package com.haothink.db.connectionpool.faeature;

import com.haothink.db.connectionpool.HoneycombConnectionPool;

/**
 * @author wanghao
 * @date 2019年06月05日 13:47
 * description: 就是对池中空闲连接做回收：
 */
public class CleanerFeature extends AbstractFeature {

    @Override
    public void doing(HoneycombConnectionPool pool) {

    }
}
