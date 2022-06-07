package it.uniba.app.user;

/**
 * {@literal <<entity>>}
 * Manages wordsmith's privileges.
 */
public class Wordsmith extends Player {
    
    /** The list of available commands. */
    private static final String[] COMMANDS = {
            "/help",
            "/gioca",
            "/abbandona",
            "/esci",
            "/nuova <parola>",
            "/mostra"
    };

    /** Defines the user's privileges. */
    private boolean wordsmith;

    /** Initializes Wordsmith object, with Wordsmith privileges. */
    public Wordsmith() {
        wordsmith = true;
    }

    /**
     * @return a String array of all possible commands Wordsmith can use.
     */
    @Override
    public String[] getHelpCommands() {
        String[] copy = new String[COMMANDS.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = COMMANDS[i];
        }
        return copy;
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean isWordsmith() {
        return wordsmith;
    }

}
