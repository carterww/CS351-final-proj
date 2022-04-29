package com.groupten.app;

import com.groupten.io.Prompts;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        boolean succLogin = true, keepGoing = true;
        char choice = '5';

        Prompts.promptWelcome();
        do {
            if (!succLogin) Prompts.promptInvalidLogin();
            succLogin = Prompts.promptLogin();

        } while (succLogin == false);
        Prompts.promptValidLogin();
        do {
            try {
                choice = Prompts.promptMainOptions();
                switch (choice) {
                    case '1':
                        Options.option1();
                        pause();
                        break;
                    case '2':
                        Options.option2(Prompts.promptGetName());
                        pause();
                        break;
                    case '3':
                        Options.option3();
                        pause();
                        break;
                    case '4':
                        Options.option4();
                        pause();
                        break;
                    case '5':
                        Options.option5();
                    default:
                        keepGoing = false;
                        System.out.println("bye");
                        break;
                }
            } catch (Exception e) {
                System.out.println("There seemed to be an error. Terminating!");
                Options.option5();
            }
        } while (keepGoing == true);
    }

    private static void pause() {
        Scanner stdin = Prompts.stdin;

        System.out.print("Type anything to continue: ");
        stdin.nextLine();
    }

}
