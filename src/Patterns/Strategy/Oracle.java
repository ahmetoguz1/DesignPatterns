/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Strategy;

import java.sql.ResultSet;

/**
 *
 * @author ahmet
 */
public class Oracle implements IDB {

    @Override
    public ResultSet select(String query) {
        System.out.println("\"Select\" command is running on \"Oracle\"");
        return null;
    }

    @Override
    public void update(String query) {
        System.out.println("\"Update\" command is running on \"Oracle\"");
    }

    @Override
    public void delete(String query) {
        System.out.println("\"Delete\" command is running on \"Oracle\"");
    }

    @Override
    public void insert(String query) {
        System.out.println("\"Insert\" command is running on \"Oracle\"");
    }
}
