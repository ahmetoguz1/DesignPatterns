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
public class UrineTest extends BillDecorator {

    Test test;
    
    public UrineTest(Test test) {
        this.test = test;
    }

    @Override
    public double getCost() {
        return 400 + test.getCost();
    }

    @Override
    public String getDescription() {
        return test.getDescription() + ", İdrar";
    }    

}
