package com.michzarnowski.michal_zarnowski_a2.model;

import com.michzarnowski.michal_zarnowski_a2.db.DBConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AvengerDb {

    public static int addAvenger(Avenger avenger) {
        return 0;
    }

    public static ArrayList<Avenger> getAvengers() {

        //List of Avengers to be returned
        ArrayList<Avenger> avengerList = new ArrayList<>();
        
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
                Class.forName(driver).newInstance();

                //Database connection
                conn = DBConnector.getConnection(driver, connUrl, database,
                        user, pass);

                //Declare a query
                String sqlQuery = "SELECT * FROM avengers";

                //Create and set the statement
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sqlQuery);

                //Loop through results
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("avengername");
                    String description = rs.getString("description");
                    int powerSourceInt = rs.getInt("powersource");

                    //Create power source object via PowerSourceDb
                    PowerSourceDb powerSourceDb = new PowerSourceDb();
                    PowerSource tempPowerSource
                            = powerSourceDb.getPowerSource(powerSourceInt);

                    //create Avenger objects based on DB data
                    Avenger tempAvenger = new Avenger(id, name, description,
                            tempPowerSource);

                    //add Avenger to ArrayList
                    avengerList.add(tempAvenger);
                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return avengerList;
    }
}
