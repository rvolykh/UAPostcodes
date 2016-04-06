package com.pikaso.rest.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.constants.Constants;
import com.pikaso.database.ConnectionPool;
import com.pikaso.entity.Region;
import com.pikaso.exceptions.ApiException;

public class RegionDao extends ADao<Region> {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegionDao.class);
    
    public RegionDao() {
        super(Constants.TABLE_NAME_REGION);
    }

    @Override
    public void createTable() {
        try (Connection con = ConnectionPool.getInstance().getConnection(); 
                Statement stat = con.createStatement()) {
            stat.executeUpdate(Constants.QUERY_CREATE_TABLE_REGION);
            LOGGER.trace("Executed - "+Constants.QUERY_CREATE_TABLE_REGION);
        } catch (SQLException e) {
            throw new ApiException(String.format(Constants.FAIL_CREATE_TABLE, 
                    Constants.TABLE_NAME_REGION), e);
        }
    }

    @Override
    protected Region createInstance(String[] args) {
        if(args.length!=2 && !args[0].matches("\\d+")){
            return null;
        }
        return new Region(Integer.parseInt(args[0]), args[1]);
    }

}
