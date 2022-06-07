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
        return COMMANDS;
    }

}
