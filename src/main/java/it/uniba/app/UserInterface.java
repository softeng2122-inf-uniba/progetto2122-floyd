package it.uniba.app;

import it.uniba.user.Player;
import it.uniba.user.Wordsmith;

/**
 * < < Boundary > > Gestisce l'interfaccia utente
 */
public class UserInterface {
    /**
     * Gestisce i comandi dati in input fuori dalla partita
     * 
     * @param user
     */
    public void getCommands(Player user) {
        while (true) {
            String userInput = UserInput.get();

            switch (userInput) {
                case "/help": {
                    Wordsmith.showHelp();
                    break;
                }
                case "/gioca": {

                    break;
                }
                default: {
                    System.out.println("Comando non riconosciuto o attualmente non disponibile. /help per visualizzare la lista dei comandi.");
                    break;
                }
            }
        }
    }

     /**
     * Prende lo user input per la parola segreta
     */
    public String inputSecretWord() {
        while (true) {
            String inputString = UserInput.get();
            return inputString;

        }
    }
}
