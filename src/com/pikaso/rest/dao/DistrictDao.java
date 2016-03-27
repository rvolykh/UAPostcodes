package com.pikaso.rest.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.pikaso.database.DBConnection;
import com.pikaso.rest.entity.District;

public class DistrictDao extends ADao<District>{
    public static enum DistrictDBQueries {
        GET_BY_ID("SELECT * FROM District WHERE id = '%s';"),
        GET_BY_FIELD("SELECT * FROM District WHERE %s = '%s';");
        private String query;

        private DistrictDBQueries(String query) {
            this.query = query;
        }

        @Override
        public String toString() {
            return query;
        }
    }
   
    public DistrictDao(){
        super();
        for (DistrictDBQueries districtDBQueries : DistrictDBQueries.values()) {
            sqlQueries.put(districtDBQueries.name(), districtDBQueries);
        }
    }
    
    @Override
    public void createTable(){
        Statement statement;
        try {
            statement = DBConnection.get().getConnection().createStatement();
            String sql = "CREATE TABLE District " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(255), " + 
                    " regionID INTEGER, " + 
                    " PRIMARY KEY ( id ))"; 
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(FAIL_QUERY_EXECUTE, e);
        }
       
    }

    @Override
    protected District createInstance(String[] args) {
        return new District(Integer.parseInt(args[0]),args[1],Integer.parseInt(args[2]));
    }

}
