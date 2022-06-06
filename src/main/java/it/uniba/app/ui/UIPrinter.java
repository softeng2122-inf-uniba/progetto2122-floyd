package it.uniba.app.ui;

import it.uniba.app.match.Guess;
import it.uniba.app.user.Player;
import it.uniba.app.utils.Colors;

public class UIPrinter {
    public UIPrinter() {
    }

    /**
     * Si occupa del disegno della griglia con i relativi caratteri e colori
     */
    public void getGrid(Guess[] guesses) {
        for (int i = 0; i < guesses.length; i++) {
            for (int j = 0; j < guesses[i].cell.length; j++) {
                System.out.print(String.format("| %s |", guesses[i].cell[j].getColor() + Colors.ANSI_BLACK
                        + guesses[i].cell[j].getCharacter() + Colors.ANSI_RESET));
            }
            System.out.println();
        }
    }

    public void getWelcome() {
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

    public void getHelp(final Player user) {
        System.out.println();
        for (String command : user.getHelpCommands()) {
            System.out.println(command);
        }
        System.out.println();
        System.out.println();
    }

    public void getMaxTriesReached(String secretWord) {
        System.out.println("Hai raggiunto il numero massimo di tentativi");
        System.out.println("La parola segreta è " + secretWord);
    }

    public void getCorrectGuessNotification(int currentGuess) {
        System.out.println("Parola segreta indovinata");
        System.out.println("Numero tentativi: " + (currentGuess + 1));
    }

    public void getLeftCorrectlyNotification() {
        System.out.println("Hai abbandonato correttamente la partita!");
    }

    public void getIncompleteGuess() {
        System.out.println("Tentantivo incompleto");
    }

    public void getExcessiveGuess() {
        System.out.println("Tentativo eccessivo");
    }

    public void getInvalidGuess() {
        System.out.println("Tentativo non valido");
    }

    public void getMatchAlreadyStarted() {
        System.out.println("La partita è già in corso!");
    }

    public void getSecretWordMissing() {
        System.out.println("Parola segreta mancante");
    }

    public void getLeaveRequestConfirmation() {
        System.out.print("Sicuro di voler abbandonare la partita? Y/N: ");
    }

    public void getExitRequestConfirmation() {
        System.out.print("Sicuro di voler uscire dal gioco? Y/N: ");
    }

    public void getInvalidOption() {
        System.out.println("Opzione non valida!");
    }

    public void getNoMatchToLeave() {
        System.out.println("Non è in corso alcuna partita da abbandonare.");
    }

    public void getShowSecretWord(String secretWord) {
        System.out.println("La parola segreta è: " + secretWord);
    }

    public void getOk() {
        System.out.println("OK");
    }

    public void getSecretWordTooShort() {
        System.out.println("Parola segreta troppo corta");
    }

    public void getSecretWordTooLong() {
        System.out.println("Parola segreta troppo lunga");
    }

    public void getInvalidSecretWord() {
        System.out.println("Parola segreta non valida!");
    }
}
