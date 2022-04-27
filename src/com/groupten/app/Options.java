package com.groupten.app;

import com.groupten.doa.Queries;
import com.groupten.io.PrintReports;
import com.groupten.io.Prompts;

import java.sql.SQLException;

// class that links all packages / classes together
public class Options {

    // generate representative report
    public static void option1() {
        try {
            PrintReports.repReport(Queries.genRepReport());
        } catch (SQLException e) {
            Queries.printException(e, "print resultset of representative");
        }
    }

    // generates price report about customer
    public static void option2(String custName) {
        if (!Queries.custInDb(custName)) {
            System.out.println("\nThat customer is not in the database.\n");
            return;
        }

        try {
            PrintReports.priceReport(Queries.genTotalQuotedPrice(custName));
        } catch (SQLException e) {
            Queries.printException(e, "print resultset of total prices");
        }
    }

    // adds representative to db
    public static void option3() {
        Prompts.promptAddRep();
    }

    // updates limit of customer
    public static void option4() {
        Prompts.promptUpdateLimit();
    }

    // closes the program by closing connection and scanner
    public static void option5() {
        Prompts.close();
        Queries.closeConnection();
    }


}
