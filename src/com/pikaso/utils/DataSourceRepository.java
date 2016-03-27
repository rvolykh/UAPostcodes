package com.pikaso.utils;


import java.sql.SQLException;

public class DataSourceRepository {
    private final static String FAILED_JDBC_DRIVER = "Failed to create JDBC Driver";
    private static volatile DataSourceRepository instance = null;

    private DataSourceRepository() {
    }

    public static DataSourceRepository get() {
        if (instance == null) {
            synchronized (DataSourceRepository.class) {
                if (instance == null) {
                    instance = new DataSourceRepository();
                }
            }
        }
        return instance;
    }

    public DataSource getMySql() {
        try {
            return new DataSource(new com.mysql.jdbc.Driver(),
                   "jdbc:mysql://localhost/uapostcodes_db", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(FAILED_JDBC_DRIVER, e);
        }
    }
}
