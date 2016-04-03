package com.pikaso.rest.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.pikaso.constants.Constants;
import com.pikaso.database.ConnectionPool;
import com.pikaso.entity.Region;

public class RegionDao extends ADao<Region> {

    public RegionDao() {
        super(Constants.TABLE_NAME_REGION);
    }

    @Override
    public void createTable() {
        Connection connection = null;
        Statement statement = null;
        try {
            if (connection != null & statement != null) {
                connection = ConnectionPool.getInstance().getConnection();
                statement = connection.createStatement();
                statement.executeUpdate(Constants.QUERY_CREATE_TABLE_REGION);
                statement.close();
                connection.close();
            } else {
                // TODO:
                throw new RuntimeException("lolka");
            }
        } catch (SQLException e) {
            throw new RuntimeException(Constants.FAIL_QUERY_EXECUTE, e);
        }

    }

    @Override
    protected Region createInstance(String[] args) {
        return new Region(Integer.parseInt(args[0]), args[1]);
    }

}
