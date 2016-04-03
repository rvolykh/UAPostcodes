package com.pikaso.rest.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.pikaso.constants.Constants;
import com.pikaso.database.DBConnection;
import com.pikaso.entity.City;

public class CityDao extends ADao<City> {

    public CityDao() {
        super(Constants.TABLE_NAME_CITY);
    }

    @Override
    public void createTable() {
        Statement statement;
        try {
            statement = DBConnection.get().getConnection().createStatement();
            statement.executeUpdate(Constants.QUERY_CREATE_TABLE_CITY);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("lolka", e);
        }

    }

    @Override
    protected City createInstance(String[] args) {
        return new City(Integer.parseInt(args[0]), args[1], args[2], Integer.parseInt(args[3]));
    }

}
