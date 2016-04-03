package com.pikaso.rest.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.pikaso.constants.Constants;
import com.pikaso.database.ConnectionPool;
import com.pikaso.entity.City;

public class CityDao extends ADao<City> {

    public CityDao() {
        super(Constants.TABLE_NAME_CITY);
    }

    @Override
    public void createTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            if (connection != null & statement != null) {
                connection = ConnectionPool.getInstance().getConnection();
                statement = connection.createStatement();
                statement.executeUpdate(Constants.QUERY_CREATE_TABLE_CITY);
                statement.close();
                connection.close();
            } else {
                // TODO:
                throw new RuntimeException("lolka");
            }
        } catch (SQLException e) {
            // TODO:
            throw new RuntimeException("lolka", e);
        }

    }

    @Override
    protected City createInstance(String[] args) {
        return new City(Integer.parseInt(args[0]), args[1], args[2], Integer.parseInt(args[3]));
    }

}
