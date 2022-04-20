package com.groupten.doa;

import com.groupten.doa.Queries;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.sql.SQLException;

public class Test {
    public static void main(String args[]) throws SQLException {
        BigDecimal limit = new BigDecimal(10000);

        Queries.setConnection("jdbc:mysql://localhost:3306/project",
                "root", "password");
        ResultSet res = Queries.genRepReport();
        while (res.next()) {
            System.out.println("Count " + res.getInt(1) +
            " aaverage balance " + res.getBigDecimal(2).floatValue() +
                    " name " + res.getString(3));
        }

        Queries.closeConnection();
    }
}
