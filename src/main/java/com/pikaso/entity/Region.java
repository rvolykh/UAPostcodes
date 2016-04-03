package com.pikaso.entity;

public class Region {
    private Integer id;
    private String name;
    
    
    public Region(){
        
    }

    public Region(Integer id, String name) {
        this.name = name;
        this.id = id;
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
    
    /**
     * TO JSON format
     */
    @Override
    public String toString(){
        return String.format("{\"id\":\"%d\", \"name\":\"%s\"}", id, name);
    }
    
}
