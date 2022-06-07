package it.uniba.app.user;

/**
 * < < entity > > Gestisce i diritti del giocatore
 */
public class Player {
    private static final String[] COMMANDS = {
            "/help",
            "/gioca",
            "/abbandona",
            "/esci"
    };

    private boolean wordsmith;

    public Player() {
        wordsmith = false;
    }

    public String[] getHelpCommands() {
        String[] copy = new String[COMMANDS.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = COMMANDS[i];
        }
        return copy;
    }

    public boolean isWordsmith() {
        return wordsmith;
    }

    protected void setWordsmith(final boolean value) {
        wordsmith = value;
    }

}
