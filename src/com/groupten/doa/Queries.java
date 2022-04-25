package com.groupten.doa;

import javax.xml.transform.Result;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;

public class Queries {
    public static DatabaseConnection dbCon = null;

    // sets the connection so queries can be done
    public static boolean login(String url, String user, String pass) {
        try {
            dbCon = new DatabaseConnection(url, user, pass);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static void createUser(String username, String password) {
        if (!connected()) return;

        String createuser = "CREATE USER ?@\'localhost\' IDENTIFIED BY ?;";
        String grantprivs = "GRANT ALL PRIVILEGES ON *.* TO ?@\'localhost\' WITH GRANT OPTION;";

        PreparedStatement createUserSt = null;
        PreparedStatement grantprivsSt = null;

        try {
            createUserSt = dbCon.getConnection().prepareStatement(createuser);
            grantprivsSt = dbCon.getConnection().prepareStatement(grantprivs);
        }  catch (SQLException e) {
            printException(e, "create statements");
        }

        try {
            createUserSt.setString(1, username);
            createUserSt.setString(2, password);
            grantprivsSt.setString(1, username);
        }  catch (SQLException e) {
            printException(e, "add parameters for creating user");
        }

        try {
            createUserSt.execute();
            grantprivsSt.execute();
        } catch (SQLException e) {
            printException(e, "create user and grant privileges");
        }
    }

    public static ResultSet genRepReport() {
        if (!connected()) return null;

        String genReport = "SELECT COUNT(*) AS RepresentedCustomers, " +
                "AVG(Balance) AS AverageCustomerBalance, CONCAT(FirstName, \' \', LastName) AS NAME " +
                "FROM REP INNER JOIN CUSTOMER ON Customer.RepNum = Rep.RepNum " +
                "GROUP BY Rep.RepNum;";
        Statement genReportStat = null;
        ResultSet res = null;
        try { genReportStat = dbCon.getConnection().createStatement(); }
        catch (SQLException e) {
            printException(e, "create statement");
        }

        try { res = genReportStat.executeQuery(genReport); }
        catch (SQLException e) {
            printException(e, "execute statement");
        }

        return res;
    }

    public static ResultSet genTotalQuotedPrice(String customerName) {
        if (!connected()) return null;

        String genReport = "SELECT SUM(QuotedPrice), CustomerName FROM OrderLine INNER JOIN Orders ON " +
                "Orders.OrderNum = OrderLine.OrderNum " +
                "INNER JOIN Customer ON Customer.CustomerNum = Orders.CustomerNum " +
                "WHERE Customer.CustomerName = ?;";

        PreparedStatement genReportStat = null;
        ResultSet res = null;
        try { genReportStat = dbCon.getConnection().prepareStatement(genReport); }
        catch (SQLException e) {
            printException(e, "initialize the prepared statement");
        }

        if (genReportStat != null) {
            try {
                genReportStat.setString(1, customerName);
            } catch (SQLException e) {
                printException(e, "set parameter");
            }

            try {
                res = genReportStat.executeQuery();
            }  catch (SQLException e) {
                printException(e, "execute statement");
            }
        }
        return res;
    }

    public static void updateCreditLimit(String customerName, BigDecimal newLimit) {
        if (!connected()) return;

        String updateLimit = "Update Customer SET CreditLimit = ? WHERE CustomerName = ?;";

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

            System.out.printf("\nSuccessfully updated %s\'s credit limit to %.2f\n", customerName, newLimit.floatValue());
        }
    }

    // Adds a rep to the database, a lot of parameters
    public static void addRep(String repNum, String lastName, String firstName, String password, String street, String city,
                              String state, String postalCode, BigDecimal commission, BigDecimal rate) {
        if (!connected()) return;
        boolean added = true;

        String insertRep = "INSERT INTO Rep (RepNum, LastName, FirstName, Street, City, State, PostalCode," +
                            "Commission, Rate)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement repPreparedStatement = null;
        try { repPreparedStatement = dbCon.getConnection().prepareStatement(insertRep); }
        catch (SQLException e) {
            printException(e, "initialize the prepared statement");
            added = false;
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
                added = false;
            }

            try {
                repPreparedStatement.executeUpdate();
                repPreparedStatement.close();
            }
            catch (SQLException e) {
                printException(e, "execute the prepared statement or close the statement");
                added = false;
            }

            if (added) {
                createUser((firstName + lastName).toLowerCase(), password);
            }

            System.out.printf("\nSuccessfully added %s to the Rep table.\n", (firstName + " " + lastName));

        }
    }

    // prints a message about why exception occurred
    public static void printException(Exception e, String unable) {
        System.out.println("Unable to " + unable + ". " + e.getMessage());
    }

    public static boolean custInDb(String name) {
        if (!connected()) return false;

        String st = "SELECT CustomerName FROM Customer WHERE CustomerName = ?";
        PreparedStatement p = null;
        ResultSet res = null;
        try { p = dbCon.getConnection().prepareStatement(st); }
        catch (SQLException e) {
            printException(e, "prepare statement lol");
        }

        try {
            p.setString(1, name);
        }  catch (SQLException e) {
            printException(e, "add customer's name as parameter");
        }

        try {
            res = p.executeQuery();
        }  catch (SQLException e) {
            printException(e, "execute customer lookup");
        }
        try {return custInDbHelp(res); }
        catch (SQLException e) {
            return false;
        }

    }

    private static boolean custInDbHelp(ResultSet res) throws SQLException {
        if (res == null) return false;
        String name = "";

        while (res.next()) {
            name = res.getString(1);
        }

        if (name.equals("") || name == null) {
            return false;
        }
        return true;
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
