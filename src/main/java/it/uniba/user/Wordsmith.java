package it.uniba.user;

/**
 * < < noECB > > Gestisce i diritti del paroliere
 */
public class Wordsmith extends Player {
    public Wordsmith() {
        isWordsmith = true;
    }

    public static void showHelp() {
        System.out.println("\n/help\n/gioca\n/abbandona\n/esci\n/nuova <parola>\n/mostra\n\n");
    }

}
