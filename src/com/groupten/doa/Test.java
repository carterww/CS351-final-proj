package com.groupten.doa;

import com.groupten.doa.Queries;
import java.math.BigDecimal;

public class Test {
    public static void main(String args[]) {
        BigDecimal limit = new BigDecimal(10000);

        Queries.setConnection("jdbc:mysql://localhost:3306/FinalProject",
                "root", "password");
        Queries.updateCreditLimit("Toys Galore", limit);

        Queries.closeConnection();
    }
}
