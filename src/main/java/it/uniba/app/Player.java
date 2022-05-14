package it.uniba.app;
public class Player {
    protected boolean isWordsmith;

    public Player(){
        isWordsmith = false;
    }

    public static void showHelp(){
        System.out.println("\n/help\n/gioca\n/abbandona (disponibile solo durante la partita)\n/esci\n\n");
    }

    public boolean getIsWordsmith(){
        return isWordsmith;  
    }


}
