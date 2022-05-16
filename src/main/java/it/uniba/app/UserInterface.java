package it.uniba.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.uniba.user.Player;
import it.uniba.user.Wordsmith;

/**
 * < < Boundary > > Gestisce l'interfaccia utente
 */
public class UserInterface {
  
    Match match;
    String lastSecretWord = null;

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
                    if (lastSecretWord == null) {
                        match = new Match(user.getIsWordsmith(), this);

                        // Supponiamo in questa versione che il giocatore sia a prescindere un paroliere
                        System.out.print("Inserire la parola segreta: ");
                        lastSecretWord = inputSecretWord();
                        match.setSecretWord(lastSecretWord);
                    } else {
                        match = new Match(user.getIsWordsmith(), lastSecretWord, this);
                    }
                    match.start();
                    lastSecretWord = match.getSecretWord();
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
     * Si occupa dell'utilizzo dei comandi durante la partita in base all'input dato
     * 
     * @param userInput
     * @param isWordsmith
     */
    public void inGameCommands(String userInput, boolean isWordsmith) {
        if (isWordsmith) {
            switch (userInput) {
                case "/help": {
                    Wordsmith.showHelp();
                    break;
                }
                case "/gioca": {
                    System.out.println("La partita è già in corso!");
                    break;
                }
                case "/abbandona": {
                    
                    break;
                }
                case "/esci": {
                    
                    break;
                }
                case "/mostra": {
                    System.out.println("La parola segreta è: " + match.getSecretWord());
                    
                    break;
                }
                default: {
                    Matcher matcher = Pattern.compile("(/nuova) ([A-Za-z]*)").matcher(userInput);
                    if (matcher.matches()) {
                        matcher.reset();
                        while (matcher.find()) {
                            int gc = matcher.groupCount();
                            if (UserInput.isValidAsWord(matcher.group(gc))) {
                                match.setSecretWord(matcher.group(gc));
                                System.out.println("OK");
                            } else {
                                if (matcher.group(gc).length() < Match.NUM_OF_CELLS) {
                                    System.out.println("Parola segreta troppo corta");
                                } else if (matcher.group(gc).length() > Match.NUM_OF_CELLS) {
                                    System.out.println("Parola segreta troppo lunga");
                                } else {
                                    System.out.println("Parola segreta non valida!");
                                }

                            }
                        }
                    } else {
                        System.out.println("Comando non riconosciuto. /help per visualizzare la lista dei comandi.");
                    }
                    break;
                }
            }
        } else {
            switch (userInput) {
                case "/help": {
                    Player.showHelp();
                    break;
                }
                case "/gioca": {
                    System.out.println("La partita è già in corso!");
                    break;
                }
                case "/abbandona": {
                
                    break;
                }
                case "/esci": {
               
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

            if (UserInput.isValidAsWord(inputString)) {
                return inputString;
            }
        }
    }

     /**
     * Si occupa del disegno della griglia
     */
    void drawMatrix() {
        for (int i = 0; i < Match.NUM_OF_GUESSES; i++) {
            for (int j = 0; j < Match.NUM_OF_CELLS; j++) {
                System.out.print("| %s |");
            }
            System.out.println();
        }
    }
}