/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Factory;

/**
 *
 * @author ahmet
 */
import java.sql.Connection;

public interface IConnection {
    public Connection connect();
}
