/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stores.management.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anis
 */
public class DataBaseConnection {

    private Connection con;

    public Connection getConnection() {
        try {
            // Driver Load in Memory
            Class.forName("com.mysql.jdbc.Driver");
            // Connection to DB
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/customerinfo", //URL 
                    "root", // database user name
                    "" // database user password
            );

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return con;
    }

    public void returnConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void returnResources(Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
