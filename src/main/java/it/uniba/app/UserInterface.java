package it.uniba.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.uniba.user.Player;
import it.uniba.user.Wordsmith;
import it.uniba.utils.Colors;

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
                case "/esci": {
                    System.out.print("Sicuro di voler uscire dal gioco? Y/N: ");
                    String answer = UserInput.get();
                    if (answer.equals("y")) {
                        System.exit(0);
                    } else if (answer.equals("n")) {
                    } else {
                        System.out.println("Inserire un'opzione valida!");
                    }
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
                    System.out.print("Sicuro di voler abbandonare la partita? Y/N: ");
                    String answer = UserInput.get().toLowerCase();
                    if (answer.equals("y")) {
                        match.setIsInProgress(false);
                    } else if (answer.equals("n")) {
                    } else {
                        System.out.println("Inserire un'opzione valida!");
                    }
                    break;
                }
                case "/esci": {
                    System.out.print("Sicuro di voler uscire dal gioco? Y/N: ");
                    String answer = UserInput.get();
                    if (answer.equals("y")) {
                        System.exit(0);
                    } else if (answer.equals("n")) {
                    } else {
                        System.out.println("Inserire un'opzione valida!");
                    }
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
                    System.out.print("Sicuro di voler abbandonare la partita? Y/N: ");
                    String answer = UserInput.get().toLowerCase();
                    if (answer.equals("y")) {
                        match.setIsInProgress(false);
                    } else if (answer.equals("n")) {
                    } else {
                        System.out.println("Inserire un'opzione valida!");
                    }
                    break;
                }
                case "/esci": {
                    System.out.print("Sicuro di voler uscire dal gioco? Y/N: ");
                    String answer = UserInput.get();
                    if (answer.equals("y")) {
                        System.exit(0);
                    } else if (answer.equals("n")) {
                    } else {
                        System.out.println("Inserire un'opzione valida!");
                    }
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
     * Si occupa del disegno della griglia con i relativi caratteri e colori
     */
    void drawMatrix(Guess[] guesses) {
        for (int i = 0; i < guesses.length; i++) {
            for (int j = 0; j < guesses[i].cell.length; j++) {
                System.out.print(String.format("| %s |", guesses[i].cell[j].getColor() + guesses[i].cell[j].getCharacter() + Colors.ANSI_RESET));
            }
            System.out.println();
        }
    }

    public void maxGuesses() {
        System.out.println("Hai raggiunto il numero massimo di tentativi");
        System.out.println("La parola segreta è " + match.getSecretWord());
    }

    public void correctGuess() {
        System.out.println("Parola segreta indovinata");
        System.out.println("Numero tentativi: " + (match.getCurrentGuess() + 1));
    }

    public void leave() {
        System.out.println("Hai abbandonato correttamente la partita!");
    }

    public void incompleteGuess() {
        System.out.println("Tentantivo incompleto");
    }

    public void excessiveGuess() {
        System.out.println("Tentativo eccessivo");
    }

    public void invalidGuess() {
        System.out.println("Tentativo non valido");
    }
}
