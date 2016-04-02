package com.pikaso.rest.entity;

public class District {
    private Integer id;
    private String name;
    private Integer idRegion;
    
    public District(){
        
    }

    public District(Integer id, String name, Integer idRegion) {
        this.id = id;
        this.name = name;
        this.idRegion = idRegion;
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
    
    /**
     * TO JSON format
     */
    @Override
    public String toString(){
        return String.format("{\"id\":\"%d\", \"name\":\"%s\"}", id, name);
    }
    
}
