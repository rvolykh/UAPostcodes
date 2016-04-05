package com.pikaso.rest.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.constants.Constants;
import com.pikaso.database.ConnectionPool;
import com.pikaso.entity.District;

public class DistrictDao extends ADao<District> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistrictDao.class);
    
    public DistrictDao() {
        super(Constants.TABLE_NAME_DISTRICT);
    }

    @Override
    public void createTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            if (connection != null) {
                statement = connection.createStatement();
                if (statement != null) {
                    statement.executeUpdate(Constants.QUERY_CREATE_TABLE_DISTRICT);
                    statement.close();
                }else{
                    LOGGER.error("Can't get Statement from Connection");
                }
                connection.close();
            }else{
                LOGGER.error("Can't get Database connection");
            }
        } catch (SQLException e) {
            LOGGER.error("Can't create table " + Constants.TABLE_NAME_DISTRICT, e);
            throw new RuntimeException("Can't create table " + Constants.TABLE_NAME_DISTRICT, e);
        }

    }

    @Override
    protected District createInstance(String[] args) {
        return new District(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2]));
    }

}
