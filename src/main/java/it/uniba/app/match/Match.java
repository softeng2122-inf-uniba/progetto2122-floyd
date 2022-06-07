package it.uniba.app.match;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.ui.UserInterface;

/**
 * < < Control > > Gestisce la partita e tutti i suoi componenti
 */

public class Match {

    public static final int NUM_OF_GUESSES = 6;
    public static final int NUM_OF_CELLS = 5;

    private Word secretWord;
    private boolean inProgress;
    private Guess[] guesses;

    private int currentGuessCtr;

    private final MatchController controller;

    public Match(UserInterface ui) {
        this.inProgress = false;
        this.currentGuessCtr = 0;
        this.secretWord = new Word();
        this.controller = new MatchController(ui, this);

        guesses = new Guess[NUM_OF_GUESSES];
        for (int i = 0; i < guesses.length; i++) {
            guesses[i] = new Guess();
        }
    }

    public MatchController getController() {
        return controller;
    }

    /**
     * This will not return a reference to the
     * real object but a clone of the requested object.
     * It preserves the internal state of the object
     * from unintentional changes.
     * If you want to intentionally save the changes
     * made on this object, use setGuesses().
     */
    public Guess[] getGuesses() {
        Guess[] guessesCopy = new Guess[NUM_OF_GUESSES];
        for (int i = 0; i < guessesCopy.length; i++) {
            guessesCopy[i] = new Guess(this.guesses[i]);
        }
        return guessesCopy;
    }

    /**
     * Updates the guesses (array) by making a new copy, so that
     * it decouples the parameter from the actual updated guesses.
     * Use this when you want to save the changes made
     * on a deep copy.
     * It preserves the internal state of the object
     * from unintentional changes.
     */
    public void setGuesses(final Guess[] newGuesses) {
        Guess[] guessesCopy = new Guess[NUM_OF_GUESSES];
        for (int i = 0; i < NUM_OF_GUESSES; i++) {
            guessesCopy[i] = new Guess(newGuesses[i]);
            this.guesses[i] = guessesCopy[i];
        }
    }

    /**
     * This will not return a reference to the
     * real object but a clone of the requested object.
     * It preserves the internal state of the object
     * from unintentional changes.
     * If you want to intentionally save the changes
     * made on this object, see setGuess().
     */
    public Guess getGuess(int idx) {
        if (idx < NUM_OF_GUESSES) {
            return new Guess(this.guesses[idx]);
        }
        return null;
    }

    /**
     * Updates the guess by making a new copy, so that
     * it decouples the parameter from the actual updated guess.
     * Use this when you want to save the changes made
     * on a deep copy.
     * It preserves the internal state of the object
     * from unintentional changes.
     */
    public void setGuess(final Guess guess, final int idx) {
        Guess guessCopy = new Guess(guess);
        this.guesses[idx] = guessCopy;
    }

    public int getCurrentGuessCtr() {
        return currentGuessCtr;
    }

    public void incrementCurrentGuessCtr() {
        this.currentGuessCtr++;
    }

    public Word getSecretWord() {
        return new Word(this.secretWord);
    }

    public void setSecretWord(String newSecretWord) {
        this.secretWord.setString(newSecretWord);
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean value) {
        inProgress = value;
    }

    public void reset() {
        this.currentGuessCtr = 0;
        this.inProgress = false;

        for (Guess guess : guesses) {
            guess.initGuess();
        }
    }

}
