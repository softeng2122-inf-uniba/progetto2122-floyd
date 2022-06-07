package it.uniba.app.match.controller;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ConsoleUtils;
import it.uniba.app.utils.ExecutableTask;
import it.uniba.app.utils.InputChecker;

public class MatchInputDispatcher implements ExecutableTask {

    private final Match match;
    private final UserInterface ui;

    public MatchInputDispatcher(Match matchObj, UserInterface uiObj) {
        this.match = matchObj;
        this.ui = uiObj;
    }

    @Override
    public void execute(String userInput) {
        if (InputChecker.isCommand(userInput)) {
            ui.getCommands(userInput);
        } else {

            if (InputChecker.isValidAsWord(userInput)) {
                updateMatchStatus(guessAttempt(userInput));
                ConsoleUtils.clearScreen();
                UserInterface.printer.getGrid(match.getGuesses());
            } else {
                UserInterface.printer.getGuessError(userInput);
            }
        }
    }

    /**
     * Requests the guess controller to make the required
     * guess examination and updates on the attempt.
     */
    private boolean guessAttempt(String userInput) {
        GuessController guess = match.getGuess(match.getCurrentGuessCtr());
        guess.examineGuessAttempt(userInput, match.getSecretWord());
        match.setGuess(guess, match.getCurrentGuessCtr());
        return guess.isCorrect();
    }

    /**
     * Checks if the given guess is marked as correct,
     * if so it ends the match,
     * if not it increments the current guess counter.
     */
    private void updateMatchStatus(boolean guessCorrect) {
        if (guessCorrect) {
            match.setInProgress(false);
        } else {
            match.incrementCurrentGuessCtr();
        }
    }

}
