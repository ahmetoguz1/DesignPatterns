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
public interface IDB {
    ResultSet select(String q);
    void update(String q);
    void delete(String q);
    void insert(String q);
}
