package com.pikaso.rest.dao;

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

public abstract class ADao<TEntity> implements IDao<TEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ADao.class);

    private final String tableName;

    protected ADao(String tableName) {
        if (tableName == null) {
            throw new IllegalArgumentException("Table name can't be null");
        }
        this.tableName = tableName;
    }

    protected abstract TEntity createInstance(String[] args);

    public abstract void createTable();

    @Override
    public TEntity getById(int id) {
        TEntity entity = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[] queryResult;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(String.format(Constants.QUERY_GET_BY_ID, tableName, id));
            if (resultSet.next()) {
                queryResult = new String[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < queryResult.length; i++) {
                    queryResult[i] = resultSet.getString(i + 1);
                }
                entity = createInstance(queryResult);
            } else {
                throw new RuntimeException(String.format(Constants.EMPTY_RESULTSET, Constants.QUERY_GET_BY_ID));
            }
        } catch (SQLException e) {
            LOGGER.error("Can't execute query 'QUERY_GET_BY_ID' in table: "+tableName,e);
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
        return entity;
    }

    @Override
    public List<TEntity> getByFieldName(String fieldName, String text) {
        List<TEntity> all = new ArrayList<TEntity>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[] queryResult;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(String.format(Constants.QUERY_GET_BY_FIELD, tableName, fieldName, text));
            while (resultSet.next()) {
                queryResult = new String[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < queryResult.length; i++) {
                    queryResult[i] = resultSet.getString(i + 1);
                }
                all.add(createInstance(queryResult));
            }
        } catch (SQLException e) {
            LOGGER.error("Can't execute query 'QUERY_GET_BY_FIELD' in table: "+tableName,e);
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
            throw new RuntimeException(String.format(Constants.EMPTY_RESULTSET, Constants.QUERY_GET_BY_FIELD));
        }
        return all;
    }

    public List<TEntity> getAll() {
        List<TEntity> all = new ArrayList<TEntity>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[] queryResult;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(String.format(Constants.QUERY_GET_ALL, tableName));
            while (resultSet.next()) {
                queryResult = new String[resultSet.getMetaData().getColumnCount()];
                for (int i = 0; i < queryResult.length; i++) {
                    queryResult[i] = resultSet.getString(i + 1);
                }
                all.add(createInstance(queryResult));
            }
        } catch (SQLException e) {
            LOGGER.error("Can't execute query 'QUERY_GET_ALL' in table: "+tableName,e);
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
            throw new RuntimeException(String.format(Constants.EMPTY_RESULTSET, Constants.QUERY_GET_ALL));
        }
        return all;
    }

}
