package com.groupten.io;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

// class that prints tables to the console
public class PrintReports {

    // prints the information about a representative in table fashion
    public static void rep(String rep[], BigDecimal repNums[]) {

        StringBuilder str = new StringBuilder();
        str.append("--------------------------------------------------------------------------------------------------------------------------------------\n");
        str.append(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "RepNum", "LastName", "FirstName", "Street", "City", "State", "PostCode", "Commission", "Rate"));

        for (int i = 0; i < rep.length; i++) {
            if (i == 3) continue;
            str.append(String.format("%-15s ", rep[i]));
        }
        for (BigDecimal b : repNums) {
            String s;
            try { s = b.floatValue() + ""; }
            catch (Exception e) { s = ""; }
            str.append(String.format("%-15s", s));
        }

        str.append("\n--------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.print(str.toString());
    }

    // prints the representative report given the result set for it
    public static void repReport(ResultSet res) throws SQLException {
        if (res == null) {
            System.out.println("An error occurred. Sorry for the inconvenience.");
            return;
        }
        StringBuilder str = new StringBuilder();
        str.append("-------------------------------------------------------------------------------------------\n");
        str.append(String.format("%26s %30s %30s\n", "Representative's name", "Average Customer Balance", "Represented Customers"));

        String tempName = "", tempRep = "";
        BigDecimal tempBal;
        while (res.next()) {
            tempName = res.getString(3);
            tempBal = res.getBigDecimal(2);
            tempRep = res.getInt(1) + "";

            str.append(String.format("%26s", tempName));
            if (tempBal != null) {
                str.append(String.format("%30s", tempBal.floatValue() + ""));
            }  else {
                str.append(String.format("%30s", ""));
            }
            str.append(String.format("%30s\n", tempRep));
        }

        str.append("-------------------------------------------------------------------------------------------\n");

        System.out.print(str.toString());
        res.close();
    }

    // prints the customer price report given the result set for it
    public static void priceReport(ResultSet res) throws SQLException {
        if (res == null) {
            System.out.println("Could not locate that customer.");
            return;
        }

        StringBuilder str = new StringBuilder();
        str.append("---------------------------------------------------------\n");
        str.append(String.format("%26s %26s\n", "Customer\'s name", "Total Order Price"));

        String tempName = null;
        BigDecimal tempQuote = null;
        while (res.next()) {
            tempName = res.getString(2);
            tempQuote = res.getBigDecimal(1);

            if (tempName != null && tempQuote != null) {
                str.append(String.format("%26s", tempName));
                str.append(String.format("%26s\n", tempQuote.floatValue() + ""));
            }
        }

        str.append("---------------------------------------------------------\n");

        System.out.print(str.toString());
        res.close();
    }
}
