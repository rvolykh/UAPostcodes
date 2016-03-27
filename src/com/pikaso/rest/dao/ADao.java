package com.pikaso.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pikaso.utils.DBConnection;

public abstract class ADao<TEntity> implements IDao<TEntity> {
    protected final static String QUERY_NOT_FOUND = "Query not found %s";
    protected final static String EMPTY_RESULTSET = "Empty ResultSet by Query %s";
    protected final static String DATABASE_READING_ERROR = "Database Reading Error";
    
    protected final HashMap<String, Enum<?>> sqlQueries;
    
    protected ADao() {
        this.sqlQueries = new HashMap<String, Enum<?>>();
    }
    
    protected abstract TEntity createInstance(String[] args);
    
    public abstract void createTable();

    @Override
    public TEntity getById(int id) {
        TEntity entity = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = sqlQueries.get("GET_BY_ID").toString();
        String[] queryResult;
        int i;
        if (query == null) {
            throw new RuntimeException(String.format(QUERY_NOT_FOUND,
                    "GET_BY_ID"));
        }
        try {
            statement = DBConnection.get().getConnection().createStatement();
            resultSet = statement.executeQuery(String.format(query, id));
            if (resultSet.next()) {
                queryResult = new String[resultSet.getMetaData().getColumnCount()];
                for (i = 0; i < queryResult.length; i++) {
                    queryResult[i] = resultSet.getString(i+1);
                }
                entity = createInstance(queryResult);
            } else {
                throw new RuntimeException(String.format(EMPTY_RESULTSET,
                        query));
            }
        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_READING_ERROR, e);
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
        return entity;
    }

    @Override
    public List<TEntity> getByFieldName(String fieldName, String text) {
        List<TEntity> all = new ArrayList<TEntity>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = sqlQueries.get("GET_BY_FIELD").toString();
        String[] queryResult;
        int i;
        if (query == null) {
            throw new RuntimeException(String.format(QUERY_NOT_FOUND,
                    "GET_BY_FIELD"));
        }
        try {
            statement = DBConnection.get().getConnection().createStatement();
            resultSet = statement.executeQuery(String.format(query, fieldName, text));
            while (resultSet.next()) {
                queryResult = new String[resultSet.getMetaData().getColumnCount()];
                for (i = 0; i < queryResult.length; i++) {
                    queryResult[i] = resultSet.getString(i+1);
                }
                all.add(createInstance(queryResult));
            }
        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_READING_ERROR, e);
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
            throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
        }
        return all;
    }

    
}
