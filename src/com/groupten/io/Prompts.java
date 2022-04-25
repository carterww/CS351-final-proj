package com.groupten.io;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import com.groupten.data.DependentVars;
import com.groupten.doa.Queries;

public class Prompts {

    public static Scanner stdin = new Scanner(System.in);

    public static char promptMainOptions() {
        String[] options = new String[5];
        String choice = "5";

        options[0] = "1) Generate a report that lists some information about representatives:\n" +
                         "their name, the average balance of their customers, and the number of\n" +
                         "represented customers.\n";
        options[1] = "2) Generate a report that displays the total price of a customer's orders.\n";
        options[2] = "3) Add a new representative to the database.\n";
        options[3] = "4) Update a customer's credit limit.\n";
        options[4] = "5) Exit.\n";

        for (String str : options) {
            System.out.print(str);
        }

        System.out.print("?: ");
        choice = stdin.nextLine();

        return choice.charAt(0);
    }

    public static String promptGetName() {
        System.out.print("Enter the Customer's name: ");
        return stdin.nextLine();
    }

    public static void promptUpdateLimit() {
        System.out.print("Please enter the customer's name: ");
        String name = stdin.nextLine();
        if (!Queries.custInDb(name)) {
            System.out.println("\nThat customer is not in the database.\n");
            return;
        }
        System.out.print("Enter their new credit limit: ");
        BigDecimal newLimit = stdin.nextBigDecimal();
        stdin.nextLine();
        Queries.updateCreditLimit(name, newLimit);
    }

    public static void promptAddRep() {
        String[] rep = new String[8];
        String pass;
        BigDecimal[] repInfoNums = new BigDecimal[2];
        System.out.println("Please enter some information about the representative that you would like to\nadd to the database.");
        helpCheckParam("Representative\'s number: ", 0,2, rep);
        helpCheckParam("Last Name: ", 1, 15, rep);
        helpCheckParam("First Name: ", 2, 15, rep);
        helpCheckParam("Street of Address: ", 4, 15, rep);
        helpCheckParam("City: ", 5, 15, rep);
        helpCheckParam("State (two letter): ", 6, 2, rep);
        helpCheckParam("Postal Code: ", 7, 5, rep);
        helpCheckParam("Commission received: ", 0, 7, repInfoNums);
        helpCheckParam("Commission rate (0.xx): ", 1, 3, repInfoNums);
        helpCheckParam("Password: ", 3, 16, rep);

        System.out.print("Please confirm the password: ");
        pass = stdin.nextLine();
        while (!pass.equals(rep[3])) {
            helpCheckParam("Password: ", 3, 16, rep);
            System.out.print("Please confirm the password: ");
            pass = stdin.nextLine();
        }

        System.out.print("Are you sure you would like to add them as a representative? (Y/N): ");
        String confirm = stdin.nextLine();

        if (confirm.charAt(0) == 'Y' || confirm.charAt(0) == 'y') {
            Queries.addRep(rep[0], rep[1], rep[2], rep[3], rep[4],
                    rep[5], rep[6], rep[7], repInfoNums[0], repInfoNums[1]);
        }  else {
            System.out.println("\nThey were not added to the database.\n");
        }
    }

    private static void helpCheckParam(String out, int index, int maxLength, String[] rep) {
        boolean tooLong = false;
        do {
            if (tooLong) System.out.println("Too lengthy. Try again");
            System.out.print(out);
            rep[index] = stdin.nextLine();
            tooLong = rep[index].length() > maxLength;
        } while (rep[index] != null && tooLong);
    }

    private static void helpCheckParam(String out, int index, int maxLength, BigDecimal[] repInfoNums) {
        BigDecimal tmp = null;
        boolean tooLong = false;
        do {
            if (tooLong) System.out.println("Too lengthy. Try again");
            System.out.print(out);
            tmp = stdin.nextBigDecimal();
            stdin.nextLine();
            repInfoNums[index] = tmp.setScale(2, RoundingMode.CEILING);
            tooLong = repInfoNums[index].precision() > maxLength;
        } while (repInfoNums[index] != null && tooLong);
    }

    public static boolean promptLogin() {
        String username, password;

        System.out.println("Please login, (if unsure of username or password, check README.txt)");
        System.out.print("Username: ");
        username = stdin.nextLine();
        System.out.print("Password: ");
        password = stdin.nextLine();

        return Queries.login(DependentVars.url +DependentVars.dbName, username, password);
    }

    public static void promptInvalidLogin() {
        System.out.println("Invalid username or password, please try again.");
    }

    public static void promptValidLogin() {
        System.out.println("Successful login. Welcome.");
    }

    public static void promptWelcome() {
        String tmp = "Welcome to our CS351 Final Project. You will be able to interact\n" +
                     "with the database in many ways. Options with a corresponding number\n" +
                     "will be displayed and type the number associated with the desired\n" +
                     "option.\n";

        System.out.print(tmp);
    }

    public static void close() {
        stdin.close();
    }

}
