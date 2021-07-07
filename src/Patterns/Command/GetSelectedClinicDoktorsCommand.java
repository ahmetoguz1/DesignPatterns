/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Command;

import DBObjects.Doctor;
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
public class GetSelectedClinicDoktorsCommand implements ICommand {

    @Override
    public ArrayList<Doctor> operation(String query) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        DBConnection db = new DBConnection();
        ResultSet rs = db.select(query);
        Doctor dt = null;
        try {
            while (rs.next()) {
                dt = new Doctor(
                        rs.getInt("doktorID"),
                        rs.getString("doktorAd"),
                        rs.getString("doktorSoyad"),
                        rs.getInt("doktorTC"),
                        rs.getString("doktorAlani"),
                        rs.getString("doktorTelefon"),
                        rs.getInt("hastaneID"),
                        rs.getInt("klinikID")
                );
                doctors.add(dt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetPatientCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        return doctors;
    }

}
