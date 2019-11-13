package com.michzarnowski.michal_zarnowski_a2.model;

import com.michzarnowski.michal_zarnowski_a2.db.DBConnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PowerSourceDb {

    public static PowerSource getPowerSource(int id) {
        
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

    public static ArrayList<PowerSource> getPowerSources() {
        
        //List of PowerSources to be returned
        ArrayList<PowerSource> powerSourcesList = new ArrayList<>();
        
        //DB specific variables
        String connUrl = "jdbc:postgresql://localhost/";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String database = "AvengerDb";
        String user = "postgres";
        String pass = "a1b2c3d3";

        //postgreSQL driver
        String driver = "org.postgresql.Driver";
        
        try {
            // This enviroment variable is how we get the database info on Heroku
            // it is populated by Heroku with what we need to connect to the provisioned database
            // It will be null on our local machines or if no db add-on
            // Info copyrights: PAUL BONENFANT
            String dbUrl = System.getenv("JDBC_DATABASE_URL");

            if (dbUrl != null && dbUrl.length() > 0) {
                conn = DBConnector.getConnection(driver, dbUrl);
            } else {
                //Load the driver
                Class.forName(driver);

                //Database connection
                conn = DBConnector.getConnection(driver, connUrl, database,
                        user, pass);
                
                //Declare a query
                String sqlQuery = "SELECT * FROM powersource";
                PreparedStatement ps = conn.prepareStatement(sqlQuery);

                //Create and set the statement
                rs = ps.executeQuery();
                
                //Loop through results
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String desc = rs.getString("description");
                    
                    //Create power source object 
                    PowerSource tempPowerSource = getPowerSource(id);
                    
                    //add to ArrayList
                    powerSourcesList.add(tempPowerSource);
                }
            }
            
            //close DB connection 
            DBConnector.closeJDBCObjects(conn, stmt, rs);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return powerSourcesList;
    }

}
