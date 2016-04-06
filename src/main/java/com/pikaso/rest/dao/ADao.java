package com.pikaso.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.constants.Constants;
import com.pikaso.database.ConnectionPool;
import com.pikaso.exceptions.ApiException;

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
    public TEntity getById(String id) {
        TEntity entity = null;
        String query = String.format(Constants.QUERY_GET_BY_ID, tableName, id);
        
        if(id==null){
            throw new ApiException("Value with this ID not exsist, table: "+this.getClass().getName());
        } 
        
        try (Connection con = ConnectionPool.getInstance().getConnection();
                PreparedStatement stat = con.prepareStatement(query);) {
            try (ResultSet rs = stat.executeQuery();) {
                while (rs.next()) {
                    String[] queryResult = new String[rs.getMetaData().getColumnCount()];
                    for (int i = 0; i < queryResult.length; i++) {
                        queryResult[i] = rs.getString(i + 1);
                    }
                    LOGGER.trace(Arrays.toString(queryResult));
                    entity = createInstance(queryResult);
                }
            }
        } catch (SQLException e) {
            throw new ApiException(String.format(Constants.FAIL_QUERY_EXECUTE, query), e);
        } 
        
        if(entity==null){
            throw new ApiException("Value with this ID not exsist, table: "+this.getClass().getName());
        }

        return entity;
    }

    @Override
    public List<TEntity> getByFieldName(String fieldName, String text) {
        List<TEntity> all = new ArrayList<TEntity>();
        String query = String.format(Constants.QUERY_GET_BY_FIELD, tableName, fieldName, text);
        
        try (Connection con = ConnectionPool.getInstance().getConnection();
                PreparedStatement stat = con.prepareStatement(query);) {
            try (ResultSet rs = stat.executeQuery();) {
                while (rs.next()) {
                    String[] queryResult = new String[rs.getMetaData().getColumnCount()];
                    for (int i = 0; i < queryResult.length; i++) {
                        queryResult[i] = rs.getString(i + 1);
                    }
                    LOGGER.trace(Arrays.toString(queryResult));
                    TEntity entity = createInstance(queryResult);
                    if(entity != null){
                        all.add(entity);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ApiException(String.format(Constants.FAIL_QUERY_EXECUTE, query), e);
        }  
        
        if (all.isEmpty()) {
            throw new ApiException(Constants.EMPTY_RESULTSET+this.getClass().getName());
        }
        
        return all;
    }

    public List<TEntity> getAll() {
        List<TEntity> all = new ArrayList<TEntity>();  
        String query = String.format(Constants.QUERY_GET_ALL, tableName);
        
        try (Connection con = ConnectionPool.getInstance().getConnection();
                PreparedStatement stat = con.prepareStatement(query);) {
            try (ResultSet rs = stat.executeQuery();) {
                while (rs.next()) {
                    String[] queryResult = new String[rs.getMetaData().getColumnCount()];
                    for (int i = 0; i < queryResult.length; i++) {
                        queryResult[i] = rs.getString(i + 1);
                    }
                    LOGGER.trace(Arrays.toString(queryResult));
                    TEntity entity = createInstance(queryResult);
                    if(entity != null){
                        all.add(entity);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ApiException(String.format(Constants.FAIL_QUERY_EXECUTE, query), e);
        }  
        
        if (all.isEmpty()) {
            throw new ApiException("Value with this ID not exsist, table: "+this.getClass().getName());
        }

        return all;
    }

}
