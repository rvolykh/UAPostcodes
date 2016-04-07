package com.pikaso.database;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.constants.Constants;

public final class ConnectionPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    private static DataSource dataSource;
    
    private ConnectionPool(){
        
    }
    
    static {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/MySQLDS");
        } catch (NamingException e) {
            try {
                dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/pool-ds");
            } catch (NamingException e1) {
                LOGGER.error("Can't establish connection with Database",e1);
            }
        }
    }

    public static DataSource getInstance() {
        if (dataSource == null){ 
            throw new RuntimeException(Constants.DATABASE_CONNECTION_ERROR);
        }
        return dataSource;
    }

}
