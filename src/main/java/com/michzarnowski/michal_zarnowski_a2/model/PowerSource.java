

package com.michzarnowski.michal_zarnowski_a2.model;

import java.io.Serializable;


public class PowerSource implements Serializable{
    
    private int id;
    private String description;
    
    public PowerSource() {
        
    }

    public PowerSource(int id, String description) {
        this.id = id;
        this.description = description;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

}
