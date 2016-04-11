package com.pikaso.entity;

public class City{
    private Integer id;
    private String name;
    private String postcode;
    private Integer destrictID;
    private Integer code;
    
    public City(){
        
    }

    public City(Integer id, String name, String postcode, Integer destrictID, Integer code) {
        this.id = id;
        this.name = name;
        this.postcode = postcode;
        this.destrictID = destrictID;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getDestrictID() {
        return destrictID;
    }

    public void setDestrictID(Integer destrictID) {
        this.destrictID = destrictID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * TO JSON format
     */
    @Override
    public String toString(){
        return String.format("{\"id\":\"%d\", \"name\":\"%s\", \"postcode\":\"%s\", \"code\":\"%d\"}", id, name, postcode, code);
    }
    
}
