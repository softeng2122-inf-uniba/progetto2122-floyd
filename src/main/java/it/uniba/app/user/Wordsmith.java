package it.uniba.app.user;

/**
 * < < entity > > Gestisce i diritti del paroliere
 */
public class Wordsmith extends Player {
    private static final String[] COMMANDS = {
            "/help",
            "/gioca",
            "/abbandona",
            "/esci",
            "/nuova <parola>",
            "/mostra"
    };

    private boolean wordsmith;

    public Wordsmith() {
        wordsmith = true;
    }

    @Override
    public String[] getHelpCommands() {
        String[] copy = new String[COMMANDS.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = COMMANDS[i];
        }
        return copy;
    }

    @Override
    public boolean isWordsmith() {
        return wordsmith;
    }

}
