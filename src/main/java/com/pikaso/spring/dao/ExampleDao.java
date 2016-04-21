package com.pikaso.spring.dao;

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
import com.pikaso.entity.City;
import com.pikaso.exceptions.ApiException;
import com.pikaso.exceptions.ExampleException;
import com.pikaso.pageable.PageHolder;


public class ExampleDao implements IExampleDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleDao.class);
    
    @Override
    public PageHolder<City> getAllPageable(int start, int count) {
        PageHolder<City> result;
        List<City> all = new ArrayList<City>();
        String query = String.format(Constants.GET_ALL_PAGEABLE, start, count);
        
        try (Connection con = ConnectionPool.getInstance().getConnection();
                PreparedStatement stat = con.prepareStatement(query);) {
            try (ResultSet rs = stat.executeQuery();) {
                while (rs.next()) {
                    String[] queryResult = new String[rs.getMetaData().getColumnCount()];
                    for (int i = 0; i < queryResult.length; i++) {
                        queryResult[i] = rs.getString(i + 1);
                    }
                    LOGGER.trace(Arrays.toString(queryResult));
                    if(queryResult.length == 5 && queryResult[0].matches("\\d+") && 
                            queryResult[3].matches("\\d+") && queryResult[4].matches("\\d+")){
                        City entity =  new City(Integer.parseInt(queryResult[0]), queryResult[1], 
                                queryResult[2], Integer.parseInt(queryResult[3]), Integer.parseInt(queryResult[4]));
                        all.add(entity);
                    }
                }
            }
        } catch (SQLException e) {
            throw new ExampleException(String.format(Constants.FAIL_QUERY_EXECUTE, query), e);
        }  
        
        if (all.isEmpty()) {
            throw new ExampleException(Constants.EMPTY_RESULTSET+this.getClass().getName());
        }
        
        result = new PageHolder<City>(all);
        result.setPage(start/count);    
        return result;
    }

    @Override
    public int getAllCount() {
        int count = 0;
        
        try (Connection con = ConnectionPool.getInstance().getConnection();
                PreparedStatement stat = con.prepareStatement(Constants.GET_ALL_PAGEABLE_COUNT);) {
            try (ResultSet rs = stat.executeQuery();) {
                while (rs.next()) {
                    count = rs.getInt(1);
                }
                LOGGER.trace(Constants.CITY_TOTAL_COUNT_MSG,count);
            }
        } catch (SQLException e) {
            throw new ExampleException(String.format(Constants.FAIL_QUERY_EXECUTE, Constants.GET_ALL_PAGEABLE_COUNT), e);
        }  
        
        if (count == 0) {
            throw new ExampleException(String.format(Constants.CITY_TOTAL_COUNT_MSG, count));
        }

        return count;
    }

}
