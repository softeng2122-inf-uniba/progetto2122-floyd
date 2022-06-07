package it.uniba.app.user;

/**
 * {@literal <<entity>>}
 * Manages player's privileges.
 */
public class Player {

    /** The list of available commands. */
    private static final String[] COMMANDS = {
            "/help",
            "/gioca",
            "/abbandona",
            "/esci"
    };

    /** Defines the user's privileges. */
    private boolean wordsmith;

    /** Initializes Player object, without Wordsmith privileges. */
    public Player() {
        wordsmith = false;
    }

    /**
     * @return a String array of all possible commands Player can use.
     */
    public String[] getHelpCommands() {
        String[] copy = new String[COMMANDS.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = COMMANDS[i];
        }
        return copy;
    }

    /**
     * @return {@code true} if the player is Wordsmith, {@code false} if not.
     */
    public boolean isWordsmith() {
        return wordsmith;
    }

}
