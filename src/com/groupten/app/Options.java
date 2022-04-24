package com.groupten.app;

import com.groupten.doa.Queries;
import com.groupten.io.PrintReports;
import com.groupten.io.Prompts;

import java.sql.SQLException;

public class Options {

    public static void option1() {
        try {
            PrintReports.repReport(Queries.genRepReport());
        } catch (SQLException e) {
            Queries.printException(e, "print resultset of representative");
        }
    }

    public static void option2(String custName) {
        try {
            PrintReports.priceReport(Queries.genTotalQuotedPrice(custName));
        } catch (SQLException e) {
            Queries.printException(e, "print resultset of total prices");
        }
    }

    public static void option3() {
        Prompts.promptAddRep();
    }
    public static void option4() {
        Prompts.promptUpdateLimit();
    }

    public static void option5() {
        Prompts.close();
        Queries.closeConnection();
    }


}
