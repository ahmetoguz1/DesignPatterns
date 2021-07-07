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
public abstract class Test {
    String description = "Unknown Test";
    
    public String getDescription()
    {
        return description;
    }
    public abstract double getCost();
}
