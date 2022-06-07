package it.uniba.app.match;

import it.uniba.app.match.controller.GuessController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.Resettable;

/**
 * < < Control > > Gestisce la partita e tutti i suoi componenti
 */

public class Match implements Resettable {

    public static final int NUM_OF_GUESSES = 6;
    public static final int NUM_OF_CELLS = 5;

    private Word secretWord;
    private boolean inProgress;
    private GuessController[] guessControllers;

    private int currentGuessCtr;

    public Match(UserInterface ui) {
        this.inProgress = false;
        this.currentGuessCtr = 0;
        this.secretWord = new Word();

        this.guessControllers = new GuessController[NUM_OF_GUESSES];
        for (int i = 0; i < guessControllers.length; i++) {
            this.guessControllers[i] = new GuessController();
        }
    }

    /**
     * This will not return a reference to the
     * real object but a clone of the requested object.
     * It preserves the internal state of the object
     * from unintentional changes.
     * If you want to intentionally save the changes
     * made on this object, use setGuesses().
     */
    public GuessController[] getGuesses() {
        GuessController[] guessControllersCopy = new GuessController[NUM_OF_GUESSES];
        for (int i = 0; i < guessControllersCopy.length; i++) {
            guessControllersCopy[i] = new GuessController(this.guessControllers[i]);
        }
        return guessControllersCopy;
    }

    /**
     * Updates the guesses (array) by making a new copy, so that
     * it decouples the parameter from the actual updated guesses.
     * Use this when you want to save the changes made
     * on a deep copy.
     * It preserves the internal state of the object
     * from unintentional changes.
     */
    public void setGuesses(final GuessController[] newGuesses) {
        GuessController[] guessControllersCopy = new GuessController[NUM_OF_GUESSES];
        for (int i = 0; i < NUM_OF_GUESSES; i++) {
            guessControllersCopy[i] = new GuessController(newGuesses[i]);
            this.guessControllers[i] = guessControllersCopy[i];
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
    public GuessController getGuess(final int idx) {
        if (idx < NUM_OF_GUESSES) {
            return new GuessController(this.guessControllers[idx]);
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
    public void setGuess(final GuessController guess, final int idx) {
        GuessController guessControllerCopy = new GuessController(guess);
        this.guessControllers[idx] = guessControllerCopy;
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

        for (GuessController guess : guessControllers) {
            guess.reset();
        }
    }

}
