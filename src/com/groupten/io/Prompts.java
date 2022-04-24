package com.groupten.io;

import java.math.BigDecimal;
import java.util.Scanner;
import com.groupten.doa.Queries;

public class Prompts {
    private static Scanner stdin = new Scanner(System.in);

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

        choice = stdin.next();

        return choice.charAt(0);
    }

    public static void promptGetRep() {
        String[] rep = new String[8];
        String pass;
        BigDecimal[] repInfoNums = new BigDecimal[2];
        System.out.println("Please enter some information about the representative that you would like to\nadd to the database.");
        System.out.print("Representative number: ");
        rep[0] = stdin.next();
        System.out.print("Last name: ");
        rep[1] = stdin.nextLine();
        System.out.print("First name: ");
        rep[2] = stdin.nextLine();
        System.out.print("Street of address: ");
        rep[4] = stdin.nextLine();
        System.out.print("City: ");
        rep[5] = stdin.nextLine();
        System.out.print("State (two letter abbreviation): ");
        rep[6] = stdin.next();
        System.out.print("Postal Code: ");
        rep[7] = stdin.next();
        System.out.print("Commission received (0 if new): ");
        repInfoNums[0] = stdin.nextBigDecimal();
        stdin.next();
        System.out.print("Commission rate: ");
        repInfoNums[1] = stdin.nextBigDecimal();
        stdin.next();

        System.out.print("Password: ");
        rep[3] = stdin.next();
        System.out.print("Please confirm the password: ");
        pass = stdin.next();

    }

    public static boolean promptLogin() {
        String username, password;

        System.out.println("Please login, (if unsure of username or password, check README.txt)");
        System.out.print("Username: ");
        username = stdin.nextLine();
        System.out.print("Password: ");
        password = stdin.nextLine();

        Queries.login("jdbc:mysql://localhost:3306/FinalProject", username, password);

        if (Queries.connected()) return true;
        else return false;
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
