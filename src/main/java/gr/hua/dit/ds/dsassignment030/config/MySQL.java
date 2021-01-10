package gr.hua.dit.ds.dsassignment030.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL
{
    public static Connection db_con_obj = null;
    public static PreparedStatement db_prep_obj = null;

    public static void makeJDBCConnection() {
        try {
            // We check that the DB Driver is available in our project.
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Congrats - Seems your MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println(
                    "Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
            e.printStackTrace();
            return;
        }
        try {
            // DriverManager: The basic service for managing a set of JDBC drivers. //We
            // connect to a DBMS.
            // Using the DriverManager, we can have many connections to different DBMS
            db_con_obj = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/ds-assignment?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", "1234");
            if (db_con_obj != null) {
                System.out.println("Connection Successful!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("MySQL Connection Failed!");
            e.printStackTrace();
            return;
        }
    }
}
