package it.uniba.app.match.controller;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.UserInput;

/**
 * {@literal <<control>>}
 * Manages a Match object and its internal state.
 * <p>
 * Provides methods to control every phase of the match.
 */
public class MatchController {

    /** Reference to the user interface object. */
    private final UserInterface ui;

    /** The match object that this controller is responsible of. */
    private final Match match;

    /**
     * @param uiObj a reference to the user interface object.
     */
    public MatchController(final UserInterface uiObj) {
        this.match = new Match(uiObj);
        this.ui = uiObj;
    }

    /**
     * Sets the secret word and requests to start the match.
     * When it terminates, it resets the match to the initial state.
     *
     * @param secretWord the secret word.
     */
    public void startMatch(final String secretWord) {
        setSecretWord(secretWord);

        match.setInProgress(true);
        UserInterface.printer.getGrid(match.getGuessControllers());
        while (match.getCurrentGuessCtr() < Match.NUM_OF_GUESSES && match.isInProgress()) {
            new MatchInputDispatcher(match, ui).execute(UserInput.get());
        }

        UserInterface.printer.getEndGameMessage(this);
        match.reset();
    }

    /** Sets the match status to {@code not in progress}. */
    public void endMatch() {
        match.setInProgress(false);
    }

    /**
     * @return {@code true} if the match is currently in progress,
     *         {@code false} otherwise.
     */
    public boolean isInProgress() {
        return match.isInProgress();
    }

    /**
     * @param value {@code true} if the match is currently in progress,
     *              {@code false} otherwise.
     */
    public void setInProgress(boolean value) {
        match.setInProgress(value);
    }

    /**
     * @return {@code true} if the latest (current) attempt
     *         is successful.
     *         {@code false} otherwise.
     */
    public boolean isCurrentGuessCorrect() {
        return match.getGuessController(match.getCurrentGuessCtr()).isCorrect();
    }

    /**
     * @return the current attempt number.
     *         Goes from 0 to {@link it.uniba.app.match.Match#NUM_OF_GUESSES}.
     */
    public int getCurrentGuessNumber() {
        return match.getCurrentGuessCtr();
    }

    /**
     * @return the string representing the current secret word.
     */
    public String getSecretWord() {
        return match.getSecretWord().getString();
    }

    /**
     * Sets a new secret word.
     *
     * @param str the new secret word.
     */
    public void setSecretWord(final String str) {
        match.setSecretWord(str);
    }
}
