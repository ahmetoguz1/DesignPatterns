/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Factory;

/**
 *
 * @author ahmet
 */
import java.sql.*;

public class MySQLConnection implements IConnection {
    @Override
    public Connection connect() {
        String username = ""; //deleted
        String password = ""; //deleted
        String dbName = "ceng431";
        String url = "" + dbName; //deleted
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            //testConnection(con);
            //System.out.println("Mysql Connection Successful");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
