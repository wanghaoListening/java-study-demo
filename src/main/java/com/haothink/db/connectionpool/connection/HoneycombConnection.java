package com.haothink.db.connectionpool.connection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wanghao
 * @date 2019年06月05日 11:39
 * description:
 */
public class HoneycombConnection extends HoneycombConnectionDecorator {


    public HoneycombConnection(Connection connection) {
        super(connection);
    }

    @Override
    public void close() throws SQLException {
        super.close();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return super.isClosed();
    }
}
