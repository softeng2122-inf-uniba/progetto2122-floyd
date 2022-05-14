package it.uniba.app;

import it.uniba.user.Wordsmith;

/**
 * Main class of the application.
 */
public final class App {



    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        Wordsmith user = new Wordsmith();
        UserInterface ui = new UserInterface();

        for (String arg : args) {
            if (arg.equals("--help") || arg.equals("-h")) {
                Wordsmith.showHelp();
            }
        }

        ui.getCommands(user);
    }
}
