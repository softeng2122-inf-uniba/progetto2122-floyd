package it.uniba.app.user;

/**
 * < < noECB > > Gestisce i diritti del giocatore
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
        return COMMANDS;
    }

    public boolean isWordsmith() {
        return wordsmith;
    }

    protected void setWordsmith(final boolean value) {
        wordsmith = value;
    }

}
