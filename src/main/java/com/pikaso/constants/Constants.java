package com.pikaso.constants;

public final class Constants {
    public static final Integer PAGEABLE_PAGE_SIZE = 20;
    
    /* Error messages */
    public static final String FAILED_JDBC_DRIVER = "Failed to create JDBC Driver";
    public static final String FAILED_REGISTRATE_DRIVER = "Failed to Registrate JDBC Driver";
    public static final String QUERY_NOT_FOUND = "Query not found %s";
    public static final String EMPTY_RESULTSET = "Empty ResultSet by Query %s";
    public static final String DATABASE_READING_ERROR = "Database Reading Error";
    public static final String FAIL_QUERY_EXECUTE = "Can't execute queury %s";

    /* Tables names */
    public static final String TABLE_NAME_CITY = "City";
    public static final String TABLE_NAME_DISTRICT = "District";
    public static final String TABLE_NAME_REGION = "Region";

    /* Queries */
    public static final String QUERY_GET_BY_ID = "SELECT * FROM %s WHERE id = '%s';";
    public static final String QUERY_GET_BY_FIELD = "SELECT * FROM %s WHERE %s = '%s';";
    public static final String QUERY_GET_ALL = "SELECT * FROM %s;";
    public static final String QUERY_CREATE_TABLE_CITY = "CREATE TABLE City (id INTEGER not NULL, "
            + "name VARCHAR(255), postalcode VARCHAR(10), districtID INTEGER, PRIMARY KEY ( id ))";
    public static final String QUERY_CREATE_TABLE_DISTRICT = "CREATE TABLE District (id INTEGER not NULL, "
            + "name VARCHAR(255), regionID INTEGER, PRIMARY KEY ( id ))";
    public static final String QUERY_CREATE_TABLE_REGION = "CREATE TABLE Region (id INTEGER not NULL, "
            + "name VARCHAR(255), PRIMARY KEY ( id ))"; 
    
    public static final String GET_ALL_PAGEABLE = "SELECT * FROM City LIMIT %d, %d;"; 
    public static final String GET_ALL_PAGEABLE_COUNT = "SELECT COUNT(*) FROM City;";
    
}
