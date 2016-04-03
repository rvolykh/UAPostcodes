package com.pikaso.rest.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pikaso.constants.Constants;
import com.pikaso.database.ConnectionPool;

public abstract class ADao<TEntity> implements IDao<TEntity> {
    private String tableName;
    
    protected ADao(String tableName) {
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
                    queryResult[i] = resultSet.getString(i+1);
                }
                entity = createInstance(queryResult);
            } else {
                throw new RuntimeException(String.format(Constants.EMPTY_RESULTSET,
                        Constants.QUERY_GET_BY_ID));
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    // TODO Warning
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
                    queryResult[i] = resultSet.getString(i+1);
                }
                all.add(createInstance(queryResult));
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    // TODO Warning
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
                    queryResult[i] = resultSet.getString(i+1);
                }
                all.add(createInstance(queryResult));
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    // TODO Warning
                }
            }
        }
        if (all.isEmpty()) {
            throw new RuntimeException(String.format(Constants.EMPTY_RESULTSET, Constants.QUERY_GET_ALL));
        }
        return all;
    }

    
}
