package com.pikaso.rest.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.constants.Constants;
import com.pikaso.database.ConnectionPool;
import com.pikaso.entity.City;
import com.pikaso.exceptions.ApiException;

public class CityDao extends ADao<City> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CityDao.class);

    public CityDao() {
        super(Constants.TABLE_NAME_CITY);
    }

    @Override
    public void createTable() {
        try (Connection con = ConnectionPool.getInstance().getConnection(); 
                Statement stat = con.createStatement()) {
            stat.executeUpdate(Constants.QUERY_CREATE_TABLE_CITY);
            LOGGER.trace("Executed - {}", Constants.QUERY_CREATE_TABLE_CITY);
        } catch (SQLException e) {
            throw new ApiException(String.format(Constants.FAIL_CREATE_TABLE, 
                    Constants.TABLE_NAME_CITY), e);
        }
    }

    @Override
    protected City createInstance(String[] args) {
        if(args.length!=4 && !args[0].matches("\\d+") && !args[3].matches("\\d+")){
            return null;
        }
        return new City(Integer.parseInt(args[0]), args[1], args[2], Integer.parseInt(args[3]));
    }

}
