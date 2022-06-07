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
        GuessController guessControllerCopy = match.getGuessController(match.getCurrentGuessCtr());
        guessControllerCopy.setSecretWordToGuess(match.getSecretWord().getString());
        guessControllerCopy.startExamination(userInput);
        match.setGuessController(guessControllerCopy, match.getCurrentGuessCtr());
        return guessControllerCopy.isCorrect();
    }

}
