/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Command;

import DBObjects.*;
import Patterns.Singleton.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmet
 */
public class GetMyVisitsCommand implements ICommand {

    //String query = "Select * from Ziyaretler where hastaID = ";
    @Override
    public ArrayList<Visit> operation(String query) {
        ArrayList<Visit> visitList = new ArrayList<>();
        DBConnection db = new DBConnection();
        ResultSet rs = db.select(query);
        Visit vs = null;
        try {
            while (rs.next()) {
                /* Get All my hospital visits*/
                vs = new Visit(
                        rs.getInt("ziyaretID"),
                        rs.getInt("hastaID"),
                        rs.getString("hastaneAdi"),
                        rs.getString("KlinikAdi"),
                        rs.getString("doktorAdi"),
                        rs.getString("doktorSoyadi"),
                        rs.getString("Tarih")
                );
                visitList.add(vs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetPatientCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        return visitList;
    }

}
