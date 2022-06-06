package it.uniba.user;

/**
 * < < noECB > > Gestisce i diritti del giocatore
 */
public class Player {
    protected boolean isWordsmith;

    public Player() {
        isWordsmith = false;
    }

    public static void showHelp() {
        System.out.println("\n/help\n/gioca\n/abbandona\n/esci\n\n");
    }

    public boolean getIsWordsmith() {
        return isWordsmith;
    }


}
