/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.GuiManager;

import Patterns.Observer.PatientInformation;
import DBObjects.Visit;
import java.util.ArrayList;

/**
 *
 * @author ahmet
 */
public class GuiManager {

    public static PatientInformation pt = null;
    public static ArrayList<Visit> vt = new ArrayList<>();
    public static boolean covidStatus;

    public static void setPatient(PatientInformation p) {
        pt = p;
    }

    public static void setVisit(ArrayList<Visit> v) {
        vt = v;
    }

    public static void setCovidStatus(boolean covidStatus) {
        GuiManager.covidStatus = covidStatus;
    }

}
