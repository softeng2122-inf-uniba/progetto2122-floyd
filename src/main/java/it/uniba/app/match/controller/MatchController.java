package it.uniba.app.match.controller;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;

/**
 * {@literal <<control>>}
 * Controls a Match object and its components. Provides methods to control every
 * phase of the match.
 */
public class MatchController {
    private final UserInterface ui;

    private final Match match;

    public MatchController(UserInterface uiObj) {
        this.match = new Match(uiObj);
        this.ui = uiObj;
    }

    public void endMatch() {
        match.setInProgress(false);
    }

    public boolean isInProgress() {
        return match.isInProgress();
    }

    public boolean isCurrentGuessCorrect() {
        return match.getGuess(match.getCurrentGuessCtr()).isCorrect();
    }

    public int getCurrentGuessNumber() {
        return match.getCurrentGuessCtr();
    }

    public String getSecretWord() {
        return match.getSecretWord().getString();
    }

    public void setSecretWord(final String str) {
        match.setSecretWord(str);
    }

    /**
     * Starts the match.
     */
    public void start(String secretWord) {
        setSecretWord(secretWord);
        new MatchStarter(match, ui).execute(null);
        UserInterface.printer.getEndGameMessage(this);
        match.reset();
    }

}
