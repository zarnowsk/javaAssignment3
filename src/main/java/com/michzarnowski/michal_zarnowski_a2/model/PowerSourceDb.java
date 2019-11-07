package com.michzarnowski.michal_zarnowski_a2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PowerSourceDb {

    public PowerSource getPowerSource(int id) {
        
        PowerSource powerSource = null;

        try {
            //postgreSQL driver
            String driver = "org.postgresql.Driver";
            
            //Load the driver
            Class.forName(driver).newInstance();
            
            //Declare and initialize connection
            Connection conn = null;
            
            //Specify DB
            String connUrl = "jdbc:postgresql://localhost/AvengerDb";
            
            //User and password
            String user = "postgres";
            String pass = "a1b2c3d3";
            
            //Connect!
            conn = DriverManager.getConnection(connUrl, user, pass);
            
            //Acquire corresponding power source from DB
            //Power source query
            String powerQuery = "SELECT * FROM powersource WHERE id = "
                    + id;
            
            //Create and set the statement
            Statement tempStmt = conn.createStatement();
            ResultSet tempRs = tempStmt.executeQuery(powerQuery);
            
            //Get result
            String powerDescription = "";
            if (tempRs.next()) {
                powerDescription = tempRs.getString("description");
            }
            
            powerSource = new PowerSource(id, powerDescription);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex);
        }
        
        return powerSource;
    }

    public ArrayList<PowerSource> getPowerSources() {
        return new ArrayList<PowerSource>();
    }

}
