package com.haothink.db.connectionpool.faeature;

import com.haothink.db.connectionpool.HoneycombConnectionPool;

/**
 * @author wanghao
 * @date 2019年06月05日 11:46
 * description: 允许我们在其内部访问连接池，并对连接池做一系列的扩展操作
 */
public abstract class AbstractFeature {

    public abstract void doing(HoneycombConnectionPool pool);
}
