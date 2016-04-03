package com.pikaso.rest.context;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pikaso.constants.Constants;
import com.pikaso.database.DBConnection;
import com.pikaso.database.DataSourceRepository;
import com.pikaso.rest.dao.CityDao;
import com.pikaso.rest.dao.DistrictDao;
import com.pikaso.rest.dao.RegionDao;

public class InitDB {
    public static void okTables(){
        DatabaseMetaData meta;
        try {
           meta = DBConnection.get(DataSourceRepository.get().getMySql()).getConnection().getMetaData();
           ResultSet rs = meta.getTables(null, null, Constants.TABLE_NAME_CITY, null);
           if(!rs.next()){
               new CityDao().createTable();
           }
           rs.close();
           rs = meta.getTables(null, null, Constants.TABLE_NAME_DISTRICT, null);
           if(!rs.next()){
               new DistrictDao().createTable();
           }
           rs.close();
           rs = meta.getTables(null, null, Constants.TABLE_NAME_REGION, null);
           if(!rs.next()){
               new RegionDao().createTable();
           }
           rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
