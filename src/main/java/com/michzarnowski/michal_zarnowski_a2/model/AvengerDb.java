package com.michzarnowski.michal_zarnowski_a2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AvengerDb {

    public int addAvenger(Avenger avenger) {
        return 0;
    }

    public ArrayList<Avenger> getAvengers() {
        
        ArrayList<Avenger> avengerList = new ArrayList<>();

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

            //Declare a query
            String sqlQuery = "SELECT * FROM avengers";

            //Create and set the statement
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            //Loop through results
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("avengername");
                String description = rs.getString("description");
                int powerSourceInt = rs.getInt("powersource");
                
                //Create power source object via PowerSourceDb
                PowerSourceDb powerSourceDb = new PowerSourceDb();
                PowerSource tempPowerSource = 
                        powerSourceDb.getPowerSource(powerSourceInt);
                
                //create Avenger objects based on DB data
                Avenger tempAvenger = new Avenger(id, name, description, 
                        tempPowerSource);
                
                //add Avenger to ArrayList
                avengerList.add(tempAvenger);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex);
        }

        return avengerList;
    }

}
