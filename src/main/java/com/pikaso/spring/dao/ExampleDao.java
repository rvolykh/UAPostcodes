package com.pikaso.spring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.constants.Constants;
import com.pikaso.database.ConnectionPool;
import com.pikaso.entity.City;
import com.pikaso.pageable.PageHolder;


public class ExampleDao implements IExampleDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleDao.class);
    
    @Override
    public PageHolder<City> getAllPageable(int start, int count) {
        PageHolder<City> result;
        List<City> all = new ArrayList<City>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[] queryResult;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(String.format(Constants.GET_ALL_PAGEABLE, start, count));
            while (resultSet.next()) {
                queryResult = new String[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < queryResult.length; i++) {
                    queryResult[i] = resultSet.getString(i + 1);
                }
                all.add(new City(Integer.parseInt(queryResult[0]), queryResult[1], queryResult[2], Integer.parseInt(queryResult[3])));
            }
        } catch (SQLException e) {
            LOGGER.error("Can't execute query 'GET_ALL_PAGEABLE'",e);
            throw new RuntimeException(Constants.DATABASE_READING_ERROR, e);
        } finally {
            if (resultSet != null) {
                try {
                   resultSet.close();
               } catch (SQLException e) {
                   LOGGER.error("Can't close ResultSet",e);
               }
           }
           if (statement != null) {
               try {
                   statement.close();
               } catch (SQLException e) {
                   LOGGER.error("Can't close Statement",e);
               }
           }
           if (connection != null) {
               try {
                   connection.close();
               } catch (SQLException e) {
                   LOGGER.error("Can't close Connection",e);
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
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Constants.GET_ALL_PAGEABLE_COUNT);
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't execute query 'GET_ALL_PAGEABLE_COUNT'",e);
            throw new RuntimeException(Constants.DATABASE_READING_ERROR, e);
        } finally {
            if (resultSet != null) {
                try {
                   resultSet.close();
               } catch (SQLException e) {
                   LOGGER.error("Can't close ResultSet",e);
               }
           }
           if (statement != null) {
               try {
                   statement.close();
               } catch (SQLException e) {
                   LOGGER.error("Can't close Statement",e);
               }
           }
           if (connection != null) {
               try {
                   connection.close();
               } catch (SQLException e) {
                   LOGGER.error("Can't close Connection",e);
               }
           }
        }
        return count;
    }

}
