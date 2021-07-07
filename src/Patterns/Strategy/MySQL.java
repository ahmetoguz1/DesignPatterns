/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Strategy;

import Patterns.Singleton.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ahmet
 */
public class MySQL implements IDB {

    @Override
    public ResultSet select(String query) {
        try {
            Connection conn = DBConnection.getConnection();

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            return rs;

            // iterate through the java resultset
            /*while (rs.next()) {
                String username = rs.getString("username");                

                // print the results
                System.out.println("---- Values from MySQL ----");
                System.out.format("%s\n", username);
            }
            st.close();*/
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        System.out.println("\"Select\" command is running on \"MySQL\"");
        return null;
    }

    @Override
    public void update(String query) {
        System.out.println("\"Update\" command is running on \"MySQL\"");
    }

    @Override
    public void delete(String query) {
        System.out.println("\"Delete\" command is running on \"MySQL\"");
    }

    @Override
    public void insert(String query) {
        System.out.println("\"Insert\" command is running on \"MySQL\"");
    }
}
