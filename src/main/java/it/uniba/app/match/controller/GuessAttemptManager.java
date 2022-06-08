package it.uniba.app.match.controller;

import it.uniba.app.match.Match;
import it.uniba.app.utils.GenericExecutableTaskString;
/**
 * {@literal <<control>>}
 * Manages the guess attempt by correctly requesting the examination
 * and returning a flag indicating if the attempt was successful.
 */
public class GuessAttemptManager implements GenericExecutableTaskString<Boolean> {

    /** Reference to the match currently in progress. */
    private final Match match;

    /**
     * @param matchObj a reference to the match currently in progress
     */
    public GuessAttemptManager(final Match matchObj) {
        this.match = matchObj;
    }

    /**
     * Requests the examination process on the requested string.
     *
     * @param userInput the string to examine.
     * @return the result of the examination,
     *         {@code true} if the secret word has been guessed,
     *         {@code false} otherwise.
     */
    @Override
    public Boolean execute(final String userInput) {
        GuessController guessControllerCopy = match.getGuessController(match.getCurrentGuessCtr());
        guessControllerCopy.setSecretWordToGuess(match.getSecretWord().getString());
        guessControllerCopy.startExamination(userInput);
        match.setGuessController(guessControllerCopy, match.getCurrentGuessCtr());
        return guessControllerCopy.isCorrect();
    }

}
