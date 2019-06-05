package com.haothink.db.connectionpool.datasource;

import com.haothink.db.connectionpool.HoneycombConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wanghao
 * @date 2019年06月05日 11:43
 * description:
 */
public class HoneycombDataSource extends HoneycombWrapperDatasource {

    private HoneycombConnectionPool connectionPool;

    private void init(){

    }

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }
}
