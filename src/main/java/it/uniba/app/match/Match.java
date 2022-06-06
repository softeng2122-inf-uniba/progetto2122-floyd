package it.uniba.app.match;

import it.uniba.app.ui.UserInterface;

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

    private final MatchController controller;

    public Match(UserInterface ui) {
        this.isInProgress = false;
        this.currentGuessCtr = 0;
        this.secretWord = new Word(null);
        this.controller = new MatchController(ui, this);

        guesses = new Guess[NUM_OF_GUESSES];
        for (int i = 0; i < guesses.length; i++) {
            guesses[i] = new Guess();
        }
    }

    public MatchController getController() {
        return controller;
    }

    public Guess[] getGuesses() {
        return guesses;
    }

    public Guess getGuess(int idx) {
        return guesses[idx];
    }

    public int getCurrentGuessCtr() {
        return currentGuessCtr;
    }

    public void incrementCurrentGuessCtr() {
        this.currentGuessCtr++;
    }

    public Word getSecretWord() {
        return secretWord;
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

}
