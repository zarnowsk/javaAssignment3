

package com.michzarnowski.michal_zarnowski_a2.model;

import java.io.Serializable;


public class Avenger implements Serializable{
    
    private int id;
    private String name;
    private String description;
    private PowerSource powerSource;
    
    public Avenger() {
        
    }

    public Avenger(int id, String name, String description, PowerSource powerSource) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.powerSource = powerSource;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public void setPowerSource(PowerSource powerSource) {
        this.powerSource = powerSource;
    }
    
    @Override
    public String toString(){
        String format = "ID = %d \n Name = %s \n Description = %s \n";
        
        return String.format(format, id, name, description);
    }

}
