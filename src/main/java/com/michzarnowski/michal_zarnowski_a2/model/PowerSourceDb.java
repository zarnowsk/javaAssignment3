package com.michzarnowski.michal_zarnowski_a2.model;

import com.michzarnowski.michal_zarnowski_a2.db.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Java class to handle database manipulation referencing the 'powersource' table
 * inside the 'AvengerDb'. This class is used to retrieve a specific power
 * source from the database or an ArrayList of all records inside the table.
 * @author Michal Zarnowski
 * @author Paul Bonenfant (code segment used to check enviroment variable)
 */
public class PowerSourceDb {

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
     * Method used to retrieve a specific power source from the database by 
     * supplying the id of the power source to retrieve.
     * @param id identifier of the power source to retrieve
     * @return required PowerSource object
     */
    public static PowerSource getPowerSource(int id) {

        //Initialize variables used by this method
        PowerSource powerSource = null;
        Connection conn = null;
        ResultSet tempRs = null;

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

                //Acquire corresponding power source from DB
                //Power source query
                String powerQuery = "SELECT * FROM powersource WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(powerQuery);
                ps.setInt(1, id); //set query param values

                //Acquire results
                tempRs = ps.executeQuery();

                //Get result
                String powerDescription = "";
                if (tempRs.next()) {
                    powerDescription = tempRs.getString("description");
                }

                powerSource = new PowerSource(id, powerDescription);
            }
            //close DB connection 
            DBConnector.closeJDBCObjects(conn, tempRs);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return powerSource;
    }

    /**
     * Method used to retrieve all PowerSource records in the database and 
     * place them insed an ArrayList to return to calling method.
     * @return ArrayList of PowerSource objects to be returned
     */
    public static ArrayList<PowerSource> getPowerSources() {

        //List of PowerSources to be returned
        ArrayList<PowerSource> powerSourcesList = new ArrayList<>();

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
            DBConnector.closeJDBCObjects(conn, rs);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return powerSourcesList;
    }

}
