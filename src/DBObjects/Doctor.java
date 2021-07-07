/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBObjects;

/**
 *
 * @author ahmet
 */
public class Doctor {

    private int doctorID;
    private String doctorName;
    private String doctorLastName;
    private int doctorTC;
    private String doctorField;
    private String doctorTel;
    private int hospitalID;
    private int clinicID;

    public Doctor(int doctorID, String doctorName, String doctorLastName, int doctorTC, String doctorField, String doctorTel, int hospitalID, int clinicID) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.doctorLastName = doctorLastName;
        this.doctorTC = doctorTC;
        this.doctorField = doctorField;
        this.doctorTel = doctorTel;
        this.hospitalID = hospitalID;
        this.clinicID = clinicID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public int getDoctorTC() {
        return doctorTC;
    }

    public String getDoctorField() {
        return doctorField;
    }

    public String getDoctorTel() {
        return doctorTel;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public int getClinicID() {
        return clinicID;
    }

}
