package it.uniba.user;

/**
 * < < noECB > > Gestisce i diritti del paroliere
 */
public class Wordsmith extends Player {
    public Wordsmith() {
        isWordsmith = true;
    }

    public static void showHelp() {
        System.out.println("\n/help\n/gioca\n/abbandona (disponibile solo durante la partita)\n/esci\n/nuova <parola> (disponibile solo durante la partita)\n/mostra (disponibile solo durante la partita)\n\n");
    }

}
