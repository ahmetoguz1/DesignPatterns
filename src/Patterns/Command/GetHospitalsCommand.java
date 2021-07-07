/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Command;

import DBObjects.Hospital;
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
public class GetHospitalsCommand implements ICommand {

    @Override
    public ArrayList<Hospital> operation(String query) {
        ArrayList<Hospital> hospitals = new ArrayList<>();
        DBConnection db = new DBConnection();
        ResultSet rs = db.select(query);
        Hospital hp = null;
        try {
            while (rs.next()) {
                hp = new Hospital(
                        rs.getInt("hastaneID"),
                        rs.getString("hastaneAdi"),
                        rs.getString("il"),
                        rs.getString("ilce")
                );
                hospitals.add(hp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetPatientCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hospitals;
    }
}
