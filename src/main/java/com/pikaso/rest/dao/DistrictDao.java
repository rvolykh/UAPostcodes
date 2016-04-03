package com.pikaso.rest.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.pikaso.constants.Constants;
import com.pikaso.database.ConnectionPool;
import com.pikaso.entity.District;

public class DistrictDao extends ADao<District> {

    public DistrictDao() {
        super(Constants.TABLE_NAME_DISTRICT);
    }

    @Override
    public void createTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            if (connection != null & statement != null) {
                connection = ConnectionPool.getInstance().getConnection();
                statement = connection.createStatement();
                statement.executeUpdate(Constants.QUERY_CREATE_TABLE_DISTRICT);
                statement.close();
                connection.close();
            } else {
                // TODO:
                throw new RuntimeException("lolka");
            }
        } catch (SQLException e) {
            // TODO:
            throw new RuntimeException(Constants.FAIL_QUERY_EXECUTE, e);
        }

    }

    @Override
    protected District createInstance(String[] args) {
        return new District(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2]));
    }

}
