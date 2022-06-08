package it.uniba.app.ui;

import static it.uniba.app.utils.ConsoleUtils.ANSI_BLACK;
import static it.uniba.app.utils.ConsoleUtils.ANSI_RESET;

import it.uniba.app.match.Match;
import it.uniba.app.match.controller.GuessController;
import it.uniba.app.match.controller.MatchController;
import it.uniba.app.user.UserController;

/**
 * {@literal <<boundary>>}
 * Holds all the output methods of the application.
 */
public class UIPrinter {

    /** Instantiates a Printer object. */
    public UIPrinter() {
    }

    /**
     * Prints the match grid with colors and letters.
     *
     * @param guessControllers reference to the current guess controllers.
     */
    public void getGrid(final GuessController[] guessControllers) {
        for (int i = 0; i < Match.NUM_OF_GUESSES; i++) {
            for (int j = 0; j < Match.NUM_OF_CELLS; j++) {
                System.out.print(String.format("[ %s ]",
                        guessControllers[i].getCellColor(j)
                                + ANSI_BLACK
                                + guessControllers[i].getCellCharacter(j)
                                + ANSI_RESET));
            }
            System.out.println();
        }
    }

    /** Prints a welcome splash screen. */
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

    /**
     * Prints the user's available commands.
     *
     * @param userController the user reference requesting the command.
     */
    public void getHelp(final UserController userController) {
        System.out.println();
        for (String command : userController.getHelpCommands()) {
            System.out.println(command);
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Prints a notification saying the
     * player has reached max number of tries.
     * It prints the secret word too.
     *
     * @param secretWord the secret word of the match.
     */
    private void getMaxTriesReached(final String secretWord) {
        System.out.println("Hai raggiunto il numero massimo di tentativi");
        System.out.println("La parola segreta è " + secretWord);
    }

    /**
     * Prints a notification saying the
     * player has guessed the secret word.
     * It prints the number of tries it took.
     *
     * @param currentGuess the counter of guesses taken.
     */
    private void getCorrectGuessNotification(final int currentGuess) {
        System.out.println("Parola segreta indovinata");
        System.out.println("Numero tentativi: " + (currentGuess + 1));
    }

    /**
     * Prints the secret word.
     *
     * @param secretWord the secret word of the match.
     */
    public void getShowSecretWord(final String secretWord) {
        System.out.println("La parola segreta è: " + secretWord);
    }

    /**
     * Prints a notification saying the
     * player has successfully left the match.
     */
    private void getLeftCorrectlyNotification() {
        System.out.println("Hai abbandonato correttamente la partita!");
    }

    /**
     * Prints an error notification, saying that
     * the guess was incomplete.
     */
    private void getIncompleteGuess() {
        System.out.println("Tentantivo incompleto");
    }

    /**
     * Prints an error notification, saying that
     * the guess was excessive.
     */
    private void getExcessiveGuess() {
        System.out.println("Tentativo eccessivo");
    }

    /**
     * Prints an error notification, saying that
     * the guess was not valid.
     */
    private void getInvalidGuess() {
        System.out.println("Tentativo non valido");
    }

    /**
     * Prints an error notification, saying that
     * the match is already in progress.
     */
    public void getMatchAlreadyStarted() {
        System.out.println("La partita è già in corso!");
    }

    /**
     * Prints an error notification, saying that
     * the secret word is missing.
     */
    public void getSecretWordMissing() {
        System.out.println("Parola segreta mancante");
    }

    /**
     * Prints a question, asking confirmation
     * to leave the match.
     */
    public void getLeaveRequestConfirmation() {
        System.out.print("Sicuro di voler abbandonare la partita? Y/N: ");
    }

    /**
     * Prints a question, asking confirmation
     * to close the game.
     */
    public void getExitRequestConfirmation() {
        System.out.print("Sicuro di voler uscire dal gioco? Y/N: ");
    }

    /**
     * Prints an error notification, saying that
     * the chosen option is not valid.
     */
    public void getInvalidOption() {
        System.out.println("Opzione non valida!");
    }

    /**
     * Prints an error notification, saying that
     * there is not a match currently in progress.
     */
    public void getNoMatchToLeave() {
        System.out.println("Non è in corso alcuna partita da abbandonare.");
    }

    /** Prints an "OK" notification. */
    public void getOk() {
        System.out.println("OK");
    }

    /**
     * Prints an error notification, saying that
     * the chosen secret word is too short.
     */
    public void getSecretWordTooShort() {
        System.out.println("Parola segreta troppo corta");
    }

    /**
     * Prints an error notification, saying that
     * the chosen secret word is too long.
     */
    public void getSecretWordTooLong() {
        System.out.println("Parola segreta troppo lunga");
    }

    /**
     * Prints an error notification, saying that
     * the chosen secret word is not valid.
     */
    public void getInvalidSecretWord() {
        System.out.println("Parola segreta non valida!");
    }

    /**
     * Prints an error notification, saying that
     * the command has not been recognised.
     * Therefore it gives advice about {@code /help} command.
     */
    public void getInvalidCommand() {
        System.out.println("Comando non riconosciuto. /help per visualizzare la lista dei comandi.");
    }

    /**
     * Decides, based on the given word,
     * which error notification to print.
     *
     * @param chosenWord the invalid word to check.
     */
    public void getGuessError(final String chosenWord) {
        if (chosenWord.length() < Match.NUM_OF_CELLS) {
            getIncompleteGuess();
        } else if (chosenWord.length() > Match.NUM_OF_CELLS) {
            getExcessiveGuess();
        } else {
            getInvalidGuess();
        }
    }

    /**
     * Decides, based on the match status,
     * which notification to print.
     *
     * @param matchController reference to the match controller object.
     */
    public void getEndGameMessage(final MatchController matchController) {
        int currGuessNumber = matchController.getCurrentGuessNumber();
        if (currGuessNumber == Match.NUM_OF_GUESSES && matchController.isInProgress()) {
            getMaxTriesReached(matchController.getSecretWord());
        } else if (matchController.isCurrentGuessCorrect()) {
            getCorrectGuessNotification(currGuessNumber);
        } else {
            getLeftCorrectlyNotification();
        }
    }
}
