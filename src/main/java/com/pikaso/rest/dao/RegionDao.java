package com.pikaso.rest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pikaso.database.DBConnection;
import com.pikaso.rest.entity.Region;

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
    
    public List<Region> getAll(){
        List<Region> all = new ArrayList<Region>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = sqlQueries.get("GET_ALL").toString();
        String[] queryResult;
        int i;
        if (query == null) {
            throw new RuntimeException(String.format(QUERY_NOT_FOUND,
                    "GET_BY_FIELD"));
        }
        try {
            statement = DBConnection.get().getConnection().createStatement();
            resultSet = statement.executeQuery(query);
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
