package com.pikaso.rest.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.constants.Constants;
import com.pikaso.database.ConnectionPool;
import com.pikaso.entity.City;

public class CityDao extends ADao<City> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityDao.class);

    public CityDao() {
        super(Constants.TABLE_NAME_CITY);
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
                    statement.executeUpdate(Constants.QUERY_CREATE_TABLE_CITY);
                    statement.close();
                }else{
                    LOGGER.error("Can't get Statement from Connection");
                }
                connection.close();
            }else{
                LOGGER.error("Can't get Database connection");
            }
        } catch (SQLException e) {
            LOGGER.error("Can't create table " + Constants.TABLE_NAME_CITY, e);
            throw new RuntimeException("Can't create table " + Constants.TABLE_NAME_CITY, e);
        }

    }

    @Override
    protected City createInstance(String[] args) {
        return new City(Integer.parseInt(args[0]), args[1], args[2], Integer.parseInt(args[3]));
    }

}
