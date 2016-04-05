package com.pikaso.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.constants.Constants;

public final class ConnectionPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    
    @SuppressWarnings("unused")
    private static final ConnectionPool connectionPool = new ConnectionPool();
    
    private static DataSource dataSource;

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            Context initialContext = (Context) ic.lookup("java:comp/env");
            if(initialContext.getEnvironment().isEmpty()){
                dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/pool-ds");
            }else{
                dataSource = (DataSource) initialContext.lookup("jdbc/MySQLDS");
            }
        } catch (NamingException e) {
            LOGGER.error("Can't establish connection with Database",e);
        }
    }

    public static DataSource getInstance() {
        if (dataSource == null)
            throw new RuntimeException(Constants.DATABASE_CONNECTION_ERROR);
        return dataSource;
    }

}
