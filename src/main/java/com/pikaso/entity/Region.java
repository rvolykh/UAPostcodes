package com.pikaso.entity;

public class Region {
    private Integer id;
    private String name;
    private Integer code;
    
    public Region(){
        
    }

    public Region(Integer id, String name, Integer code) {
        this.name = name;
        this.id = id;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
