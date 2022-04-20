package com.groupten.doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;

public class Queries {
    public static DatabaseConnection dbCon = null;

    // sets the connection so queries can be done
    public static void setConnection(String url, String user, String pass) {
        dbCon = new DatabaseConnection(url, user, pass);
    }

    public static void updateCreditLimit(String customerName, BigDecimal newLimit) {
        if (!connected()) return;

        String updateLimit = "Update Customer SET CreditLimit = ? WHERE CustomerName = ?";

        PreparedStatement limitPreparedStatement = null;
        try { limitPreparedStatement = dbCon.getConnection().prepareStatement(updateLimit); }
        catch (SQLException e) {
            printException(e, "initialize the prepared statement");
        }

        if (limitPreparedStatement != null) {
            try {
                limitPreparedStatement.setBigDecimal(1, newLimit);
                limitPreparedStatement.setString(2, customerName);
            }  catch (SQLException e) {
                printException(e, "add parameters to the prepared statement");
            }

            try {
                limitPreparedStatement.executeUpdate();
                limitPreparedStatement.close();
            }
            catch (SQLException e) {
                printException(e, "execute the prepared statement or close the statement");
            }

            System.out.printf("\nSuccessfully updated %s\'s credit limit to %.2f", customerName, newLimit.floatValue());
        }
    }

    // Adds a rep to the database, a lot of parameters
    public static void addRep(String repNum, String lastName, String firstName, String street, String city,
                              String state, String postalCode, BigDecimal commission, BigDecimal rate) {
        if (!connected()) return;

        String insertRep = "INSERT INTO Rep (RepNum, LastName, FirstName, Street, City, State, PostalCode," +
                            "Commission, Rate)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement repPreparedStatement = null;
        try { repPreparedStatement = dbCon.getConnection().prepareStatement(insertRep); }
        catch (SQLException e) {
            printException(e, "initialize the prepared statement");
        }
        if (repPreparedStatement != null) {
            try {
                repPreparedStatement.setString(1, repNum);
                repPreparedStatement.setString(2, lastName);
                repPreparedStatement.setString(3, firstName);
                repPreparedStatement.setString(4, street);
                repPreparedStatement.setString(5, city);
                repPreparedStatement.setString(6, state);
                repPreparedStatement.setString(7, postalCode);
                repPreparedStatement.setBigDecimal(8, commission);
                repPreparedStatement.setBigDecimal(9, rate);
            } catch (SQLException e) {
                printException(e, "add parameters to the prepared statement");
            }

            try {
                repPreparedStatement.executeUpdate();
                repPreparedStatement.close();
            }
            catch (SQLException e) {
                printException(e, "execute the prepared statement or close the statement");
            }

            System.out.printf("\nSuccessfully added %s to the Rep table.\n", (firstName + lastName));

        }
    }

    // prints a message about why exception occurred
    private static void printException(Exception e, String unable) {
        System.out.println("Unable to " + unable + ". " + e.getMessage());
    }

    // returns true if database is connected
    public static boolean connected() {
        if (dbCon == null)
            return false;

        else return true;
    }

    // closes the connection
    public static void closeConnection() {
        try { dbCon.getConnection().close(); }
        catch (SQLException e) {
            System.out.println("Unable to close the connection to the database. " + e.getMessage());
        }
    }
}
