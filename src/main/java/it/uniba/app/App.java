package it.uniba.app;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.ui.control.HelpRequestProcessor;
import it.uniba.app.user.UserController;
import it.uniba.app.utils.UserInput;

/**
 * Main class of the application.
 */
public final class App {

    private App(){
    }

    /**
     * @param args command line arguments
     */

    public static void main(final String[] args) {
        UserController userController = new UserController("Wordsmith");
        UserInterface ui = new UserInterface(userController);

        UserInterface.printer.getWelcome();

        for (String arg : args) {
            if (arg.equals("--help") || arg.equals("-h")) {
                new HelpRequestProcessor(userController).execute();
                break;
            }
        }

        while (true) {
            ui.getCommands(UserInput.get());
        }

    }
}
