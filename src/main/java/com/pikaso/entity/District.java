package com.pikaso.entity;

public class District {
    private Integer id;
    private String name;
    private Integer code;
    private Integer idRegion;
    
    public District(){
        
    }

    public District(Integer id, String name, Integer code, Integer idRegion) {
        this.id = id;
        this.name = name;
        this.idRegion = idRegion;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
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
        return String.format("{\"id\":\"%d\", \"name\":\"%s\", \"code\":\"%d\"}", id, name, code);
    }
    
}
