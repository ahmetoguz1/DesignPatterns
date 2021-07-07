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
public class Hospital {
    private int hospitalID;
    private String hospitalName;
    private String city;
    private String ilce;

    public Hospital(int hospitalID, String hospitalName, String city, String ilce) {
        this.hospitalID = hospitalID;
        this.hospitalName = hospitalName;
        this.city = city;
        this.ilce = ilce;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getCity() {
        return city;
    }

    public String getIlce() {
        return ilce;
    }
    
    
}
