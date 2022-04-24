package com.groupten.io;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintReports {

    public static String repReporttoString(ResultSet res) throws SQLException {
        StringBuilder str = new StringBuilder();
        str.append("-----------------------------------\n");
        str.append(String.format("%26s %26s %16s\n", "Representative's name", "Average Customer Balance", "Represented Customers"));

        String tempName = "", tempBal = "", tempRep = "";
        while (res.next()) {
            tempName = res.getString(3);
            tempBal = res.getBigDecimal(2).floatValue() + "";
            tempRep = res.getInt(1) + "";

            str.append(String.format("%26s", tempName));
            str.append(String.format("%26s", tempBal));
            str.append(String.format("%16s\n", tempRep));
        }

        str.append("--------------------------------------\n");

        return str.toString();
    }

    public static String priceReporttoString(ResultSet res) throws SQLException {
        StringBuilder str = new StringBuilder();
        str.append("-----------------------------------\n");
        str.append(String.format("%26s %26s\n", "Customer\'s name", "Total Order Price"));

        String tempName = "", tempQuote = "";
        while (res.next()) {
            tempName = res.getString(2);
            tempQuote = res.getBigDecimal(1).floatValue() + "";

            str.append(String.format("%26s", tempName));
            str.append(String.format("%26s", tempQuote));
        }

        str.append("--------------------------------------\n");

        return str.toString();
    }
}
