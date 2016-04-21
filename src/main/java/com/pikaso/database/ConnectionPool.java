package com.pikaso.database;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.pikaso.constants.Constants;
import com.pikaso.exceptions.ExampleException;

public final class ConnectionPool {
    private static DataSource dataSource;
    
    private ConnectionPool(){
        
    }
    
    static {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/MySQLDS");
        } catch (NamingException e) {
            throw new ExampleException(Constants.DATABASE_CONNECTION_ERROR, e);
        }
    }

    public static DataSource getInstance() {
        if (dataSource == null){ 
            throw new ExampleException(Constants.DATABASE_CONNECTION_ERROR);
        }
        return dataSource;
    }

}
