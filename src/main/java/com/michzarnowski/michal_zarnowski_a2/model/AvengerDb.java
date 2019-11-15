package com.michzarnowski.michal_zarnowski_a2.model;

import com.michzarnowski.michal_zarnowski_a2.db.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Java class to handle database manipulation referencing the 'avengers' table
 * inside the 'AvengerDb'. This class is used to retrieve  an ArrayList of all 
 * records inside the table or to add a new Avenger object to the database.
 * @author Michal Zarnowski
 * @author Paul Bonenfant (code segment used to check enviroment variable)
 */
public class AvengerDb {

    //postgreSQL driver
    private static String driver = "org.postgresql.Driver";
    //connection URL
    private static String connUrl = "jdbc:postgresql://localhost/";
    //database name
    private static String database = "AvengerDb";
    //database credentials
    private static String user = "postgres";
    private static String pass = "a1b2c3d3";

    /**
     * Method used to add a new Avenger object to the database.
     * @param avenger Avenger object to be added
     * @return number of rows added to the database
     */
    public static int addAvenger(Avenger avenger) {

        //Avenger details to be stored in the DB
        int id = avenger.getId();
        String name = avenger.getName();
        String description = avenger.getDescription();
        int powerSource = avenger.getPowerSource().getId();

        //DB specific variables
        Connection conn = null;
        String table = "avengers";
        int queryResult = 0;

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
                String sqlQuery = "INSERT INTO " + table
                        + "(id, avengername, description, powersource) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sqlQuery);
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, description);
                ps.setInt(4, powerSource);

                //Execute query
                queryResult = ps.executeUpdate();
            }

            //Close DB connection
            DBConnector.closeJDBCObjects(conn);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return queryResult;
    }

    /**
     * Method used to retrieve all Avenger records in the database and 
     * place them insed an ArrayList to return to calling method.
     * @return ArrayList of Avenger objects to be returned
     */
    public static ArrayList<Avenger> getAvengers() {

        //List of Avengers to be returned
        ArrayList<Avenger> avengerList = new ArrayList<>();

        //DB specific variables
        Connection conn = null;
        ResultSet rs = null;

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
                String sqlQuery = "SELECT * FROM avengers";
                PreparedStatement ps = conn.prepareStatement(sqlQuery);

                //Acquire results
                rs = ps.executeQuery();

                //Loop through results
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("avengername");
                    String description = rs.getString("description");
                    int powerSourceInt = rs.getInt("powersource");

                    //Create power source object via PowerSourceDb
                    PowerSource tempPowerSource
                            = PowerSourceDb.getPowerSource(powerSourceInt);

                    //create Avenger objects based on DB data
                    Avenger tempAvenger = new Avenger(id, name, description,
                            tempPowerSource);

                    //add Avenger to ArrayList
                    avengerList.add(tempAvenger);
                }
            }

            //Close DB connection
            DBConnector.closeJDBCObjects(conn, rs);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return avengerList;
    }
}
