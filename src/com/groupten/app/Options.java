package com.groupten.app;

import com.groupten.doa.Queries;
import com.groupten.io.PrintReports;

import java.sql.SQLException;

public class Options {

    public static void option1() {
        try {
            PrintReports.repReporttoString(Queries.genRepReport());
        } catch (SQLException e) {
            Queries.printException(e, "print resultset of representative");
        }
    }

    public static void option2(String custName) {
        try {
            PrintReports.priceReporttoString(Queries.genTotalQuotedPrice(custName));
        } catch (SQLException e) {
            Queries.printException(e, "print resultset of total prices");
        }
    }



}
