/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Decorator;

/**
 *
 * @author ahmet
 */
public class GeneralTest extends Test{
    
    public GeneralTest() {
        description = "Genel Muayene";
    }

    @Override
    public double getCost() {
        return 200;
    }
}
