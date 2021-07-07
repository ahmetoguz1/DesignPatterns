/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Observer;

import Gui.GuiManager.GuiManager;
import java.util.Calendar;

/**
 *
 * @author ahmet
 */
public class PatientInformation implements IObserver {

    private int patientID;
    private int patientTC;
    private String patientName;
    private String patientLastName;
    private String patientBorn;
    private String patientBirthDate;
    private String patientAddress;
    private String patientTel;
    private String patientEmail;
    private String patientPassword;

    public PatientInformation(int patientID, int patientTC, String patientName, String patientLastName, String patientBorn, String patientBirthDate, String patientAddress, String patientTel, String patientEmail, String patientPassword) {
        this.patientID = patientID;
        this.patientTC = patientTC;
        this.patientName = patientName;
        this.patientLastName = patientLastName;
        this.patientBorn = patientBorn;
        this.patientBirthDate = patientBirthDate;
        this.patientAddress = patientAddress;
        this.patientTel = patientTel;
        this.patientEmail = patientEmail;
        this.patientPassword = patientPassword;
    }

    public int getPatientID() {
        return patientID;
    }

    public int getPatientTC() {
        return patientTC;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public String getPatientBorn() {
        return patientBorn;
    }

    public String getPatientBirthDate() {
        return patientBirthDate;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public String getPatientTel() {
        return patientTel;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public String getPatientPassword() {
        return patientPassword;
    }

    @Override
    public void update(int age) {
        int year = Calendar.getInstance().get(Calendar.YEAR);

        //System.out.println("PatientBirthDate: " + patientBirthDate);
        String[] d = patientBirthDate.split("\\.");

        int patientAge = year - Integer.parseInt(d[2]);

        if (patientAge >= age) {
            System.out.println(this.patientName + " " + this.patientLastName + " aşı sıranız geldi. Aşı olmak için randevu alabilirsiniz.");
            /* Change status of covid Vaccine of this user */
            GuiManager.setCovidStatus(true);
        }
    }

}
