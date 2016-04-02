package com.pikaso.rest.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.pikaso.database.DBConnection;
import com.pikaso.rest.entity.City;

public class CityDao extends ADao<City>{
    public static enum CityDBQueries {
        GET_BY_ID("SELECT * FROM City WHERE id = '%s';"),
        GET_BY_FIELD("SELECT * FROM City WHERE %s = '%s';");
        private String query;

        private CityDBQueries(String query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }
    }
   
    public CityDao(){
        super();
        for (CityDBQueries cityDBQueries : CityDBQueries.values()) {
            sqlQueries.put(cityDBQueries.name(), cityDBQueries);
        }
    }
    
    @Override
    public void createTable(){
        Statement statement;
        try {
            statement = DBConnection.get().getConnection().createStatement();
            String sql = "CREATE TABLE City " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(255), " + 
                    " postalcode VARCHAR(10), " + 
                    " districtID INTEGER, " + 
                    " PRIMARY KEY ( id ))"; 
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("lolka", e);
        }
       
    }

    @Override
    protected City createInstance(String[] args) {
        return new City(Integer.parseInt(args[0]),args[1],args[2],Integer.parseInt(args[3]));
    }

}
