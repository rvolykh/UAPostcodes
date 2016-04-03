package com.pikaso.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pikaso.database.DBConnection;
import com.pikaso.entity.Region;

public class RegionDao  extends ADao<Region>{
    private final static String FAIL_QUERY_EXECUTE = "Can't execute queury %s";
    
    public static enum RegionDBQueries {
        GET_BY_ID("SELECT * FROM Region WHERE id = '%s';"),
        GET_BY_FIELD("SELECT * FROM Region WHERE %s = '%s';"),
        GET_ALL("SELECT * FROM Region;");
        private String query;

        private RegionDBQueries(String query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }
    }
   
    public RegionDao(){
        super();
        for (RegionDBQueries regionDBQueries : RegionDBQueries.values()) {
            sqlQueries.put(regionDBQueries.name(), regionDBQueries);
        }
    }
    
    @Override
    public void createTable(){
        Statement statement;
        try {
            statement = DBConnection.get().getConnection().createStatement();
            String sql = "CREATE TABLE Region " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(255), " + 
                    " PRIMARY KEY ( id ))"; 
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(FAIL_QUERY_EXECUTE, e);
        }
       
    }

    @Override
    protected Region createInstance(String[] args) {
        return new Region(Integer.parseInt(args[0]),args[1]);
    }

}
