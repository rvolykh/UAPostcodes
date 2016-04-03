package com.pikaso.rest.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.pikaso.constants.Constants;
import com.pikaso.database.DBConnection;
import com.pikaso.entity.Region;

public class RegionDao  extends ADao<Region>{
   
    public RegionDao(){
        super(Constants.TABLE_NAME_REGION);
    }
    
    @Override
    public void createTable(){
        Statement statement;
        try {
            statement = DBConnection.get().getConnection().createStatement();
            statement.executeUpdate(Constants.QUERY_CREATE_TABLE_REGION);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(Constants.FAIL_QUERY_EXECUTE, e);
        }
       
    }

    @Override
    protected Region createInstance(String[] args) {
        return new Region(Integer.parseInt(args[0]),args[1]);
    }

}
