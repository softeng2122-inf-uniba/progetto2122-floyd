package it.uniba.app.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.uniba.app.match.Guess;
import it.uniba.app.match.Match;
import it.uniba.app.user.Player;
import it.uniba.app.user.Wordsmith;
import it.uniba.app.utils.UserInput;
import it.uniba.app.utils.Colors;

/**
 * < < Boundary > > Gestisce l'interfaccia utente
 */
public class UserInterface {

    private static final UICommands commands = new UICommands();

    private Match match;
    private final Player user;
    private String lastSecretWord = null;

    public UserInterface(final Player userObj) {
        this.user = userObj;
    }

    /**
     * Gestisce i comandi dati in input fuori dalla partita
     * 
     * @param user
     */
    public void getCommands(String userInput) {
        if (user.getIsWordsmith()) {
            switch (userInput) {
                case "/help": {
                    Wordsmith.showHelp();
                    break;
                }
                case "/gioca": {
                    try {
                        if (match.getIsInProgress()) {
                            System.out.println("La partita è già in corso!");
                        } else {
                            if (lastSecretWord == null) {
                                System.out.println("Parola segreta mancante");
                            } else {
                                match = new Match(user, lastSecretWord, this);
                                match.start();
                            }
                        }
                    } catch (NullPointerException e) {
                        if (lastSecretWord == null) {
                            System.out.println("Parola segreta mancante");
                        } else {
                            match = new Match(user, lastSecretWord, this);
                            match.start();
                        }

                    }
                    break;
                }
                case "/abbandona": {
                    try {
                        if (match.getIsInProgress()) {
                            System.out.print("Sicuro di voler abbandonare la partita? Y/N: ");
                            String answer = UserInput.get();
                            if (answer.equals("y")) {
                                match.setIsInProgress(false);
                            } else if (answer.equals("n")) {
                            } else {
                                System.out.println("Inserire un'opzione valida!");
                            }
                        } else {
                            System.out.println("Non è in corso alcuna partita da abbandonare.");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Non è in corso alcuna partita da abbandonare.");
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
                    if (lastSecretWord == null)
                        System.out.println("Non è stata ancora impostata una parola segreta.");
                    else
                        System.out.println("La parola segreta è: " + lastSecretWord);
                    break;
                }

                default: {
                    Matcher matcher = Pattern.compile("(/nuova) (.+)").matcher(userInput);
                    if (matcher.matches()) {
                        matcher.reset();
                        while (matcher.find()) {
                            int gc = matcher.groupCount();
                            if (UserInput.isValidAsWord(matcher.group(gc))) {
                                try {
                                    if (match.getIsInProgress())
                                        match.setSecretWord(matcher.group(gc));
                                } catch (NullPointerException e) {
                                } finally {
                                    lastSecretWord = matcher.group(gc);
                                    System.out.println("OK");
                                }
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
                    try {
                        if (match.getIsInProgress()) {
                            System.out.println("La partita è già in corso!");
                        } else {
                            if (lastSecretWord == null) {
                                System.out.println("Parola segreta mancante");
                            } else {
                                match = new Match(user, lastSecretWord, this);
                                match.start();
                            }
                        }
                    } catch (NullPointerException e) {
                        if (lastSecretWord == null) {
                            System.out.println("Parola segreta mancante");
                        } else {
                            match = new Match(user, lastSecretWord, this);
                            match.start();
                        }
                    }
                    break;
                }
                case "/abbandona": {
                    try {
                        if (match.getIsInProgress()) {
                            System.out.print("Sicuro di voler abbandonare la partita? Y/N: ");
                            String answer = UserInput.get();
                            if (answer.equals("y")) {
                                match.setIsInProgress(false);
                            } else if (answer.equals("n")) {
                            } else {
                                System.out.println("Inserire un'opzione valida!");
                            }
                        } else {
                            System.out.println("Non è in corso alcuna partita da abbandonare.");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Non è in corso alcuna partita da abbandonare.");
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
                    System.out.println("Comando non riconosciuto. /help per visualizzare la lista dei comandi.");
                    break;
                }
            }
        }
    }

    /**
     * Prende lo user input per la parola segreta
     * 
     * @return restituisce l'input dato se è valido
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
    public void drawMatrix(Guess[] guesses) {
        for (int i = 0; i < guesses.length; i++) {
            for (int j = 0; j < guesses[i].cell.length; j++) {
                System.out.print(String.format("| %s |", guesses[i].cell[j].getColor() + Colors.ANSI_BLACK
                        + guesses[i].cell[j].getCharacter() + Colors.ANSI_RESET));
            }
            System.out.println();
        }
    }

    public void drawWelcome() {
        System.out.println(" __      __                .___.__          ");
        System.out.println("/  \\    /  \\___________  __| _/|  |   ____  ");
        System.out.println("\\   \\/\\/   /  _ \\_  __ \\/ __ | |  | _/ __ \\ ");
        System.out.println(" \\        (  <_> )  | \\/ /_/ | |  |_\\  ___/ ");
        System.out.println("  \\__/\\  / \\____/|__|  \\____ | |____/\\___  >");
        System.out.println("       \\/                   \\/           \\/ |");
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println("Benvenuto su Wordle!");
        System.out.println("Digitare /help per la lista dei comandi.");
        System.out.println();
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
