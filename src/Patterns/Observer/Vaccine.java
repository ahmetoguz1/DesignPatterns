/* Vaccine
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmet
 */
public class Vaccine {

    List<PatientInformation> patients = new ArrayList<>();
    int age;

    public void addPatient(PatientInformation u) {
        patients.add(u);
    }

    public void removePatient(PatientInformation u) {
        patients.remove(u);
    }

    public void notifyPatients(int age) {
        /* this part also known as iterator pattern */
        patients.forEach((usr) -> {            
            usr.update(age);
        });
    }
    public void setVaccineAge(int age)
    {
        this.age = age;
        notifyPatients(age);
    }
}