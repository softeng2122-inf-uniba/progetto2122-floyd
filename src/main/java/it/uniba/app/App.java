package it.uniba.app;

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

        for (String arg : args) {
            if (arg.equals("--help") || arg.equals("-h")) {
                Wordsmith.showHelp();
            }
        }

        while (true) {
            String userInput = UserInput.get();

            switch (userInput) {
                case "/help": {
                    Wordsmith.showHelp();
                    break;
                }
                default: {
                    System.out.println("Comando non riconosciuto o attualmente non disponibile. /help per visualizzare la lista dei comandi.");
                    break;
                }
            }
        }
    }
}
