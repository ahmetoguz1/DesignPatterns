/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Command;

/**
 *
 * @author ahmet
 */
public class CommandMaker {

    public Object makeCommand(ICommand cmd, String query) {
        return cmd.operation(query);
        //return null;
    }
}
