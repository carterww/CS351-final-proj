package com.groupten.io;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintReports {

    public static void repReport(ResultSet res) throws SQLException {
        if (res == null) {
            System.out.println("An error occurred. Sorry for the inconvenience.");
            return;
        }
        StringBuilder str = new StringBuilder();
        str.append("-------------------------------------------------------------------------------------------\n");
        str.append(String.format("%26s %30s %30s\n", "Representative's name", "Average Customer Balance", "Represented Customers"));

        String tempName = "", tempBal = "", tempRep = "";
        while (res.next()) {
            tempName = res.getString(3);
            tempBal = res.getBigDecimal(2).floatValue() + "";
            tempRep = res.getInt(1) + "";

            str.append(String.format("%26s", tempName));
            str.append(String.format("%30s", tempBal));
            str.append(String.format("%30s\n", tempRep));
        }

        str.append("--------------------------------------------------------------------------------------------\n");

        System.out.print(str.toString());
    }

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
    }
}
