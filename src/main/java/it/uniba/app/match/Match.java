package it.uniba.app.match;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.UserInput;
import it.uniba.app.utils.Colors;

/**
 * < < Control > > Gestisce la partita e tutti i suoi componenti
 */

public class Match {

    public static final int NUM_OF_GUESSES = 6;
    public static final int NUM_OF_CELLS = 5;

    private Word secretWord;
    private boolean isInProgress;
    private Guess[] guesses;

    private int currentGuessCtr;

    UserInterface ui;

    public Match(UserInterface ui) {
        this.isInProgress = false;
        this.currentGuessCtr = 0;
        this.secretWord = new Word(null);
        this.ui = ui;

        guesses = new Guess[6];
        for (int i = 0; i < guesses.length; i++) {
            guesses[i] = new Guess();
        }
    }

    public int getCurrentGuessCtr() {
        return currentGuessCtr;
    }

    public String getSecretWord() {
        return secretWord.getWord();
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = new Word(secretWord);
    }

    public boolean getIsInProgress() {
        return isInProgress;
    }

    public void setIsInProgress(boolean value) {
        isInProgress = value;
    }

    /**
     * Inizia la partita
     */
    public void start() {
        isInProgress = true;

        UserInterface.printer.getGrid(guesses);

        update();
    }

    /**
     * Si occupa dell'aggiornamento della partita ad ogni input
     */
    private void update() {
        while (currentGuessCtr < 6 && isInProgress) {
            String userInput = UserInput.get();

            if (!UserInput.isCommand(userInput)) {
                tryGuess(userInput);
            } else {
                ui.getCommands(userInput);
            }
            UserInterface.printer.getGrid(guesses);
        }
        if (currentGuessCtr == 6 && isInProgress) {
            UserInterface.printer.getMaxTriesReached(secretWord.getWord());
        } else if (guesses[currentGuessCtr].getIsCorrect()) {
            UserInterface.printer.getCorrectGuessNotification(currentGuessCtr);
        } else {
            UserInterface.printer.getLeftCorrectlyNotification();
        }

    }

    /**
     * Si occupa di verificare il tentativo corrente
     * 
     * @param chosenWord parola scelta per il tentativo corrente
     */
    private void tryGuess(String chosenWord) {
        if (UserInput.isValidAsWord(chosenWord)) {
            guesses[currentGuessCtr].setChosenWord(chosenWord);
            if (guesses[currentGuessCtr].checkGuess(secretWord)) {
                isInProgress = false;
            } else {
                currentGuessCtr++;
                secretWord.resetMarked();
                Colors.clearScreen();
            }
        } else if (chosenWord.length() < 5) {
            UserInterface.printer.getIncompleteGuess();
        } else if (chosenWord.length() > 5) {
            UserInterface.printer.getExcessiveGuess();
        } else {
            UserInterface.printer.getInvalidGuess();
        }
    }
}
