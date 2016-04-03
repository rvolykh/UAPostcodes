package com.pikaso.rest.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.pikaso.constants.Constants;
import com.pikaso.database.DBConnection;
import com.pikaso.entity.District;

public class DistrictDao extends ADao<District>{

    public DistrictDao(){
        super(Constants.TABLE_NAME_DISTRICT);
    }
    
    @Override
    public void createTable(){
        Statement statement;
        try {
            statement = DBConnection.get().getConnection().createStatement();
            statement.executeUpdate(Constants.QUERY_CREATE_TABLE_DISTRICT);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(Constants.FAIL_QUERY_EXECUTE, e);
        }
       
    }

    @Override
    protected District createInstance(String[] args) {
        return new District(Integer.parseInt(args[0]),args[1],Integer.parseInt(args[2]));
    }

}
