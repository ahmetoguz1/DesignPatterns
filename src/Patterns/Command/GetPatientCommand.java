/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Command;

import Patterns.Observer.PatientInformation;
import Patterns.Singleton.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmet
 */
public class GetPatientCommand implements ICommand {

    @Override
    public PatientInformation operation(String query) {
        DBConnection db = new DBConnection();
        ResultSet rs = db.select(query);
        PatientInformation pt = null;
        try {
            while (rs.next()) {
                pt = new PatientInformation(
                        rs.getInt("hastaID"),
                        rs.getInt("hastaTC"),
                        rs.getString("hastaAdi"),
                        rs.getString("hastaSoyadi"),
                        rs.getString("hastaDogumYeri"),
                        rs.getString("hastaDogumTarihi"),
                        rs.getString("hastaAdres"),
                        rs.getString("hastaTel"),
                        rs.getString("hastaEmail"),
                        rs.getString("hastaSifre")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetPatientCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pt;
    }

}
