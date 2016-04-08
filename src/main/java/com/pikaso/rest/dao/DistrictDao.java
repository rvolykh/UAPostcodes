package com.pikaso.rest.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pikaso.constants.Constants;
import com.pikaso.database.ConnectionPool;
import com.pikaso.entity.District;
import com.pikaso.exceptions.ApiException;

public class DistrictDao extends ADao<District> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistrictDao.class);
    
    public DistrictDao() {
        super(Constants.TABLE_NAME_DISTRICT);
    }

    @Override
    public void createTable() {
        try (Connection con = ConnectionPool.getInstance().getConnection(); 
                Statement stat = con.createStatement()) {
            stat.executeUpdate(Constants.QUERY_CREATE_TABLE_DISTRICT);
            LOGGER.trace("Executed - {}", Constants.QUERY_CREATE_TABLE_DISTRICT);
        } catch (SQLException e) {
            throw new ApiException(String.format(Constants.FAIL_CREATE_TABLE, 
                    Constants.TABLE_NAME_DISTRICT), e);
        }

    }

    @Override
    protected District createInstance(String[] args) {
        if(args.length!=3 && !args[0].matches("\\d+") && !args[2].matches("\\d+")){
            return null;
        }
        return new District(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2]));
    }

}
