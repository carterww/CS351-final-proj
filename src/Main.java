import com.groupten.app.Options;
import com.groupten.io.Prompts;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner stdin = new Scanner(System.in);
        boolean succLogin = true, keepGoing = true;
        char choice = '5';

        Prompts.promptWelcome();
        do {
            if (!succLogin) Prompts.promptInvalidLogin();
            succLogin = Prompts.promptLogin();

        } while (succLogin == false);
        Prompts.promptValidLogin();
        do {
            choice = Prompts.promptMainOptions();
            switch (choice) {
                case '1':
                    Options.option1();
                    System.out.print("Type anything to continue: ");
                    stdin.nextLine();
                    break;
                case '2':
                    Options.option2(Prompts.promptGetName());
                    System.out.print("Type anything to continue: ");
                    stdin.nextLine();
                    break;
                case '3':
                    Options.option3();
                    break;
                case '4':
                    Options.option4();
                    break;
                case '5':
                    Options.option5();
                default:
                    keepGoing = false;
                    System.out.println("bye");
                    break;
            }
        } while (keepGoing == true);

    }

}
