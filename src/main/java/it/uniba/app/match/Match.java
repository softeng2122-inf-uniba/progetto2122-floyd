package it.uniba.app.match;

import it.uniba.app.match.controller.GuessController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.Resettable;

/**
 * {@literal <<entity>>}
 * Represents the match settings and state.
 * <p>
 * Settings are about the maximum
 * {@link #NUM_OF_GUESSES},
 * {@link #NUM_OF_CELLS} and
 * {@link #secretWord}.
 * <p>
 * Managed by a controller.
 *
 * @see it.uniba.app.match.controller.MatchController
 * @see Resettable
 */
public class Match implements Resettable {

     /** Maximum number of attempts. */
    public static final int NUM_OF_GUESSES = 6;

    /** Maximum length of words. */
    public static final int NUM_OF_CELLS = 5;

    /** Secret word of the match. */
    private Word secretWord;

    /** Tells whether the match is currently in progress or not. */
    private boolean inProgress;

    /**
     * Holds the guess attempts of the match.
     * <p>
     * {@link #NUM_OF_GUESSES} = {@value #NUM_OF_GUESSES}.
     */
    private GuessController[] guessControllers;

    /**
     * Holds the current attempt number.
     * <p>
     * Goes from 0 to {@link #NUM_OF_GUESSES} = {@value #NUM_OF_GUESSES}
     */
    private int currentGuessCtr;

    /**
     * Instantiates a new match.
     *
     * @param ui a reference to the User Interface object
     */
    public Match(final UserInterface ui) {
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
     * <p>
     * If you want to intentionally save the changes
     * made on this object, see {@link #setGuesses}.
     *
     * @return a deep copy of the guesses (array).
     */
    public GuessController[] getGuessControllers() {
        GuessController[] guessControllersCopy = new GuessController[NUM_OF_GUESSES];
        for (int i = 0; i < guessControllersCopy.length; i++) {
            guessControllersCopy[i] = new GuessController(this.guessControllers[i]);
        }
        return guessControllersCopy;
    }

    /**
     * Updates the guesses (array) by making a new copy, so that
     * it decouples the parameter from the actual updated guesses.
     * <p>
     * Use this when you want to save the changes made
     * on a deep copy.
     * <p>
     * It preserves the internal state of the object
     * from unintentional changes.
     *
     * @param newGuessControllers the guess controllers you want to save.
     */
    public void setGuessControllers(final GuessController[] newGuessControllers) {
        GuessController[] guessControllersCopy = new GuessController[NUM_OF_GUESSES];
        for (int i = 0; i < NUM_OF_GUESSES; i++) {
            guessControllersCopy[i] = new GuessController(newGuessControllers[i]);
            this.guessControllers[i] = guessControllersCopy[i];
        }
    }

      /**
     * This will not return a reference to the
     * real object but a clone of the requested object.
     * It preserves the internal state of the object
     * from unintentional changes.
     * <p>
     * If you want to intentionally save the changes
     * made on this object, see {@link #setGuess}.
     *
     * @param idx the index of the requested guess.
     * @return a deep copy of the requested guess.
     * @see #getCurrentGuessCtr()
     */
    public GuessController getGuessController(final int idx) {
        if (idx < NUM_OF_GUESSES) {
            return new GuessController(this.guessControllers[idx]);
        }
        return null;
    }

    /**
     * Updates the guess by making a new copy, so that
     * it decouples the parameter from the actual updated guess.
     * <p>
     * Use this when you want to save the changes made
     * on a deep copy.
     * <p>
     * It preserves the internal state of the object
     * from unintentional changes.
     *
     * @param newGuessController the guess controller you want to save.
     * @param idx                the index of the guess you want to update.
     * @see #getCurrentGuessCtr()
     */
    public void setGuessController(final GuessController newGuessController, final int idx) {
        GuessController guessControllerCopy = new GuessController(newGuessController);
        this.guessControllers[idx] = guessControllerCopy;
    }

     /**
     * @return the current attempt number.
     *         Goes from 0 to {@link #NUM_OF_GUESSES} = {@value #NUM_OF_GUESSES}
     */
    public int getCurrentGuessCtr() {
        return currentGuessCtr;
    }

    /** Increments the current attempt number. */
    public void incrementCurrentGuessCtr() {
        this.currentGuessCtr++;
    }

    /** Resets to {@code 0} the current attempt number. */
    public void resetCurrentGuessCtr() {
        this.currentGuessCtr = 0;
    }

    /**
     * Use this for read-only purposes.
     * <p>
     * This will not return a reference to the
     * real object but a clone of the requested object.
     * It preserves the internal state of the object
     * from unintentional changes.
     * <p>
     * If you want to update the secret word,
     * use {@link #setSecretWord} instead.
     *
     * @return a deep copy of the requested secret word.
     */
    public Word getSecretWord() {
        return new Word(this.secretWord);
    }

    /**
     * Sets a new secret word.
     *
     * @param newSecretWord the new secret word.
     */
    public void setSecretWord(final String newSecretWord) {
        this.secretWord.setString(newSecretWord);
    }

    /**
     * @return {@code true} if match is currently in progress,
     *         {@code false} otherwise.
     */
    public boolean isInProgress() {
        return inProgress;
    }

    /**
     * @param value {@code true} if match is currently in progress,
     *              {@code false} otherwise.
     */
    public void setInProgress(final boolean value) {
        inProgress = value;
    }

    /**
     * Resets the match to default state,
     * but holds the secret word.
     */
    public void reset() {
        this.currentGuessCtr = 0;
        this.inProgress = false;

        for (GuessController guessController : guessControllers) {
            guessController.reset();
        }
    }

}
