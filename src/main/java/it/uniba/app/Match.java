package it.uniba.app;

import it.uniba.utils.Colors;

/**
 * < < Control > > Gestisce la partita e tutti i suoi componenti
 */

public class Match {

    public static final int NUM_OF_GUESSES = 6;
    public static final int NUM_OF_CELLS = 5;

    private Word secretWord;
    private boolean isInProgress;
    private Guess[] guesses;

    private int currentGuess = 0;

    UserInterface ui;

    private boolean isWordsmith; // utilizzato per capire quali comandi accettare durante il match

    /**
     * Istanzia un match, senza assegnazione diretta della parola segreta
     * 
     * @param isWordsmith
     */
    public Match(boolean isWordsmith, UserInterface ui) {
        isInProgress = false;
        secretWord = new Word(null);
        this.isWordsmith = isWordsmith;
        this.ui = ui;

        guesses = new Guess[6];
        for (int i = 0; i < guesses.length; i++) {
            guesses[i] = new Guess();
        }
    }

    /**
     * Istanzia un match, assegnando direttamente la parola segreta
     * 
     * @param isWordsmith
     * @param secretWord
     * @param ui
     */
    public Match(boolean isWordsmith, String secretWord, UserInterface ui) {
        isInProgress = false;
        this.secretWord = new Word(secretWord);
        this.isWordsmith = isWordsmith;
        this.ui = ui;

        guesses = new Guess[6];
        for (int i = 0; i < guesses.length; i++) {
            guesses[i] = new Guess();
        }
    }

    public int getCurrentGuess() {
        return currentGuess;
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

        ui.drawMatrix(guesses);

        update();
    }

    /**
     * Si occupa dell'aggiornamento della partita ad ogni input
     */
    private void update() {
        while (currentGuess < 6 && isInProgress) {
            String userInput = UserInput.get();

            if (!UserInput.isCommand(userInput)) {
                tryGuess(userInput);
            } else {
                ui.inGameCommands(userInput, isWordsmith);
            }
            ui.drawMatrix(guesses);
        }
        if (currentGuess == 6 && isInProgress) {
            ui.maxGuesses();
        } else if (guesses[currentGuess].getIsCorrect()) {
            ui.correctGuess();
        } else {
            ui.leave();
        }

    }

    /**
     * Si occupa di verificare il tentativo corrente
     * 
     * @param chosenWord parola scelta per il tentativo corrente
     */
    private void tryGuess(String chosenWord) {
        if (UserInput.isValidAsWord(chosenWord)) {
            guesses[currentGuess].setChosenWord(chosenWord);
            if (guesses[currentGuess].checkGuess(secretWord)) {
                isInProgress = false;
            } else {
                currentGuess++;
                secretWord.resetMarked();
                Colors.clearScreen();
            }
        } else if (chosenWord.length() < 5) {
            ui.incompleteGuess();
        } else if (chosenWord.length() > 5) {
            ui.excessiveGuess();
        } else {
            ui.invalidGuess();
        }
    }
}
