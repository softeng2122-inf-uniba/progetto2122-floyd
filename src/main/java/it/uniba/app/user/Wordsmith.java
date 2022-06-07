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

    public Wordsmith() {
        super();
        setWordsmith(true);
    }

    @Override
    public String[] getHelpCommands() {
        String[] copy = new String[COMMANDS.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = COMMANDS[i];
        }
        return copy;
    }

}
