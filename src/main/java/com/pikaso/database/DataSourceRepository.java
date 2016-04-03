package com.pikaso.database;


import java.sql.SQLException;

import com.pikaso.constants.Constants;

public class DataSourceRepository {
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
            throw new RuntimeException(Constants.FAILED_JDBC_DRIVER, e);
        }
    }
}
