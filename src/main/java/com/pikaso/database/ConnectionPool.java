package com.pikaso.database;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.constants.Constants;


public final class ConnectionPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    
    private static DataSource dataSource = initDataSource();
    
    private static DataSource initDataSource() {
        try {
            DataSource result = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/MySQLDS");
            return result;
        } catch (NamingException e) {
            try {
                DataSource result = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/pool-ds");
                return result;
            } catch (NamingException e1) {
                LOGGER.error("Can't establish connection with Database",e);
            }
        }
        return null;
    }

    public static DataSource getInstance() {
        if (dataSource == null)
            throw new RuntimeException(Constants.DATABASE_CONNECTION_ERROR);
        return dataSource;
    }

}
