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
public abstract class BillDecorator extends Test{
    
    
    public abstract String getDescription();
    
    /*protected IBill decoratedBill;

    public BillDecorator(IBill decoratedBill) {
        this.decoratedBill = decoratedBill;
    }
    
    public void createBill()
    {
        decoratedBill.createBill();
    }*/
    
}
