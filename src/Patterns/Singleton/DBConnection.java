/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patterns.Singleton;

/**
 *
 * @author ahmet
 */
import java.sql.Connection;
import Patterns.Factory.*;
import Patterns.Strategy.*;
import java.sql.ResultSet;

/*
    this class used for singleton database connection
 */
public class DBConnection {

    /*
        created objects static for using singleton pattern
     */
    private static ConnectionFactory connectionFactory;
    private static Connection instance = null;

    /*
        Used for adapter pattern, it connects Strategy pattern with singleton pattern
        In our case, we can use strategy pattern objects in singleton
     */
    private static IDB db = null;

    public DBConnection() {
        getConnection();
    }

    public enum DatabaseType {
        MYSQL, POSTGRE, Oracle;
    }

    public DBConnection(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /*
        Added Singleton to the database if there is no database connection create one
     */
    public static Connection getConnection() {
        if (instance == null) {
            IConnection connection = connectionFactory.getConnection();
            instance = connection.connect();
        }
        return instance;
    }

    public static ConnectionFactory getConnectionFactory(DatabaseType databaseType) {
        switch (databaseType) {
            case MYSQL:
                db = new MySQL();
                return new MySQLConnectionFactory();
            default:
                db = new PostgreSQL();
                return new PostgreSQLConnectionFactory();
        }
    }

    /*
        Adapter pattern
     */
    public ResultSet select(String query) {
        //getConnection();
        return db.select(query);
    }

}
