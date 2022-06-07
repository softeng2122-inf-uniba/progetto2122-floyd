package it.uniba.app.match.controller;

import it.uniba.app.match.Guess;
import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ConsoleUtils;
import it.uniba.app.utils.InputChecker;
import it.uniba.app.utils.UserInput;

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

    /**
     * Starts the match.
     */
    public void start() {
        match.setInProgress(true);

        UserInterface.printer.getGrid(match.getGuesses());

        update();
    }

    /**
     * Responsible of updating the match state at each user input.
     */
    private void update() {
        while (match.getCurrentGuessCtr() < Match.NUM_OF_GUESSES && match.isInProgress()) {
            dispatchInput(UserInput.get());
        }
    }

    /**
     * Dispatches the user input depending on whether
     * the input is a command or a guess.
     */
    private void dispatchInput(String userInput) {
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
        Guess guess = match.getGuess(match.getCurrentGuessCtr());
        guess.getController().examineGuessAttempt(userInput, match.getSecretWord());
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
