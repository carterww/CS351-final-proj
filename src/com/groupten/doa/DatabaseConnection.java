package com.groupten.doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection dbCon;

    public DatabaseConnection(String url, String username, String password) {
        try {
            dbCon = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Unable to connect to the database. " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return dbCon;
    }

    public void closeConnection() {
        try { dbCon.close(); }
        catch (SQLException e) {
            System.out.println("Unable to close the connection to the database. " + e.getMessage());
        }
    }

}
