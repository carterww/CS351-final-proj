package com.groupten.app;

import com.groupten.doa.Queries;
import com.groupten.io.*;

public class Main {

    public static void main(String args[]) {
        boolean succLogin = true;
        char choice = '5';

        Prompts.promptWelcome();
        do {
            if (!succLogin) Prompts.promptInvalidLogin();
            succLogin = Prompts.promptLogin();

        } while (succLogin == false);
        Prompts.promptValidLogin();
        choice = Prompts.promptMainOptions();
        switch (choice) {
            case '1' :
                break;
            case '2' :
                break;
            case '3' :
                break;
            case '4' :
                break;
            case '5' :
                break;
            default :
                break;

        }

    }

}
