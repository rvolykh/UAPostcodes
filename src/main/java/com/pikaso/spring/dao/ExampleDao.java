package com.pikaso.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pikaso.constants.Constants;
import com.pikaso.database.DBConnection;
import com.pikaso.entity.City;
import com.pikaso.pageable.PageHolder;

public class ExampleDao implements IExampleDao {

    @Override
    public PageHolder<City> getAllPageable(int start, int count) {
        PageHolder<City> result;
        List<City> all = new ArrayList<City>();
        Statement statement = null;
        ResultSet resultSet = null;
        String[] queryResult;
        try {
            statement = DBConnection.get().getConnection().createStatement();
            resultSet = statement.executeQuery(String.format(Constants.GET_ALL_PAGEABLE, start, count));
            while (resultSet.next()) {
                queryResult = new String[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < queryResult.length; i++) {
                    queryResult[i] = resultSet.getString(i + 1);
                }
                all.add(new City(Integer.parseInt(queryResult[0]), queryResult[1], queryResult[2], Integer.parseInt(queryResult[3])));
            }
        } catch (SQLException e) {
            throw new RuntimeException(Constants.DATABASE_READING_ERROR, e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception ex) {
                    // TODO Warning
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    // TODO Warning
                }
            }
        }
        if (all.isEmpty()) {
            throw new RuntimeException(String.format(Constants.EMPTY_RESULTSET, Constants.GET_ALL_PAGEABLE));
        }
        result = new PageHolder<City>(all);
        result.setPage(start/count);
        //result.setPageSize(DaoConstants.PAGEABLE_PAGE_SIZE);
        return result;
    }

    @Override
    public int getAllCount() {
        int count = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DBConnection.get().getConnection().createStatement();
            resultSet = statement.executeQuery(Constants.GET_ALL_PAGEABLE_COUNT);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(Constants.DATABASE_READING_ERROR, e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    // TODO Warning
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // TODO Warning
                }
            }
        }
        return count;
    }

}
