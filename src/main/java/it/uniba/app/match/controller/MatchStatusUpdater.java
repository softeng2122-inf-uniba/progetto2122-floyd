package it.uniba.app.match.controller;

import it.uniba.app.match.Match;
import it.uniba.app.utils.ExecutableTaskString;

/**
 * {@literal <<control>>}
 * Checks if the given guess is marked as correct,
 * if so it ends the match,
 * if not it increments the current guess counter.
 */
public class MatchStatusUpdater implements ExecutableTaskString {

    /** Reference to the match currently in progress. */
    private final Match match;

    /**
     * @param matchObj a reference to the match currently in progress.
     */
    public MatchStatusUpdater(final Match matchObj) {
        this.match = matchObj;
    }

    /**
     * Checks the given flag,
     * if the guess is marked as correct, it ends the match.
     * It increments the current guess counter otherwise.
     *
     * @param guessCorrect a boolean transformed to string.
     * @see Boolean#toString()
     * @see Match
     */
    @Override
    public void execute(final String guessCorrect) {
        if (Boolean.parseBoolean(guessCorrect)) {
            match.setInProgress(false);
        } else {
            match.incrementCurrentGuessCtr();
        }
    }

}
