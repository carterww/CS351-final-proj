package com.groupten.io;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import com.groupten.data.DependentVars;
import com.groupten.doa.DatabaseConnection;
import com.groupten.doa.Queries;

// class that holds all user interaction text/input fetching
public class Prompts {

    public static Scanner stdin = new Scanner(System.in);

    // returns the user's option char # and prints these options
    // this is the "meat" of the menu
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

    // gets a string but used for getting the customer's name for db queries
    public static String promptGetName() {
        System.out.print("Enter the Customer's name: ");
        return stdin.nextLine();
    }

    // asks user for information that allows the customer's credit limit to be changed
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

    // asks user for information that allows a representative to be added to the rep table
    public static void promptAddRep() {
        String[] rep = new String[8];
        String pass;
        BigDecimal[] repInfoNums = new BigDecimal[2];

        System.out.println("Please enter some information about the representative that you would like to\nadd to the database.");

        // getting information and putting into arrays
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

        // gets the password for user login
        System.out.print("Please confirm the password: ");
        pass = stdin.nextLine();
        while (!pass.equals(rep[3])) {
            helpCheckParam("Password: ", 3, 16, rep);
            System.out.print("Please confirm the password: ");
            pass = stdin.nextLine();
        }

        // prints out all rep information to confirm if want add
        PrintReports.rep(rep, repInfoNums);
        System.out.print("Would you like to add them to the database? (Y/N): ");
        char answer = stdin.nextLine().charAt(0);

        // if user inputs y, rep gets added, otherwise all info gets thrown away
        if (answer == 'Y' || answer == 'y') {
            Queries.addRep(rep[0], rep[1], rep[2], rep[3], rep[4],
                    rep[5], rep[6], rep[7], repInfoNums[0], repInfoNums[1]);
            System.out.printf("Their username will be: %s%s%s\n", rep[2], rep[1], rep[0]);
        }  else {
            System.out.println("They were not added.");
        }
    }

    // gets user input, checks if string is too long, and adds to array
    private static void helpCheckParam(String out, int index, int maxLength, String[] rep) {
        boolean tooLong = false;
        do {
            if (tooLong) System.out.println("Too lengthy. Try again");

            System.out.print(out);
            rep[index] = stdin.nextLine();
            tooLong = rep[index].length() > maxLength;
        } while (rep[index] != null && tooLong);
    }

    // gets user input, checks if bigdecimal is too big and sets scale to 2, and adds to array
    private static void helpCheckParam(String out, int index, int maxLength, BigDecimal[] repInfoNums) {
        BigDecimal tmp = null;
        boolean tooLong = false;

        do {
            if (tooLong) System.out.println("Too lengthy. Try again");

            System.out.print(out);
            tmp = stdin.nextBigDecimal();
            stdin.nextLine();
            repInfoNums[index] = tmp.setScale(2, RoundingMode.CEILING); // sets to 2 decimal places
            tooLong = repInfoNums[index].precision() > maxLength; // checks if number is too big
        } while (repInfoNums[index] != null && tooLong);
    }

    // gets username and password from user
    public static boolean promptLogin() {
        String username, password;

        System.out.println("Please login, (if unsure of username or password, check README.txt)");
        System.out.print("Username: ");
        username = stdin.nextLine();

        System.out.print("Password: ");
        password = stdin.nextLine();

        // login to database and returns false if login failed
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
