package com.pikaso.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final static String FAILED_REGISTRATE_DRIVER = "Failed to Registrate JDBC Driver";
    private static volatile DBConnection instance = null;

    private Connection connection = null;
    private DataSource dataSource;

    private DBConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static DBConnection get(DataSource dataSource) {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection(dataSource);
                    try {
                        DriverManager.registerDriver(dataSource.getJdbcDriver());
                    } catch (SQLException e) {
                        throw new RuntimeException(FAILED_REGISTRATE_DRIVER);
                    }
                }
            }
        }
        return instance;
    }

    public static DBConnection get() {
        if (instance == null) {
            throw new RuntimeException(FAILED_REGISTRATE_DRIVER);
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            synchronized (DBConnection.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(dataSource.getConnectionUrl(),
                                dataSource.getUsername(), dataSource.getPassword());
                    } catch (SQLException e) {
                        throw new RuntimeException(FAILED_REGISTRATE_DRIVER, e);
                    }
                }
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (instance != null) {
            if (instance.getConnection() != null) {
                try {
                    instance.getConnection().close();
                } catch (SQLException e) {
                    throw new RuntimeException(FAILED_REGISTRATE_DRIVER, e);
                }
            }
        }
    }

}
