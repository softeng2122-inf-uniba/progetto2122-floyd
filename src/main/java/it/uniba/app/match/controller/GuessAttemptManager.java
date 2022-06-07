package it.uniba.app.match.controller;

import it.uniba.app.match.Match;
import it.uniba.app.utils.ExecutableTaskGeneric;

public class GuessAttemptManager implements ExecutableTaskGeneric<Boolean> {

    private final Match match;

    public GuessAttemptManager(Match matchObj) {
        this.match = matchObj;
    }

    @Override
    public Boolean execute(String userInput) {
        GuessController guessCopy = match.getGuess(match.getCurrentGuessCtr());
        guessCopy.setSecretWordToGuess(match.getSecretWord().getString());
        guessCopy.startExamination(userInput);
        match.setGuess(guessCopy, match.getCurrentGuessCtr());
        return guessCopy.isCorrect();
    }

}
