package com.pikaso.database;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.pikaso.constants.Constants;

public final class ConnectionPool {
    @SuppressWarnings("unused")
    private static final ConnectionPool connectionPool = new ConnectionPool();
    
    private static DataSource dataSource;

    private ConnectionPool() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/pool-ds");
        } catch (NamingException e) {
            // TODO msg, only log
        }
    }

    public static DataSource getInstance() {
        if (dataSource == null)
            throw new RuntimeException(Constants.DATABASE_CONNECTION_ERROR);
        return dataSource;
    }

}
