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
public class Visit {

    private int visitID;
    private int patientID;
    private String hospitalName;
    private String clinicName;
    private String doctorName;
    private String doctorLastName;
    private String date;

    public Visit(int visitID, int patientID, String hospitalName, String clinicName, String doctorName, String doctorLastName, String date) {
        this.visitID = visitID;
        this.patientID = patientID;
        this.hospitalName = hospitalName;
        this.clinicName = clinicName;
        this.doctorName = doctorName;
        this.doctorLastName = doctorLastName;
        this.date = date;
    }

    public int getVisitID() {
        return visitID;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public String getDate() {
        return date;
    }

}
