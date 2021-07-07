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
public class BloodValuesTest extends BillDecorator{

    Test test;
    
    public BloodValuesTest(Test test) {
        this.test = test;
    }

    @Override
    public double getCost() {
        return 400 + test.getCost();
    }

    @Override
    public String getDescription() {
        return test.getDescription() + ", Kan Değerleri";
    }    
    
    
}
