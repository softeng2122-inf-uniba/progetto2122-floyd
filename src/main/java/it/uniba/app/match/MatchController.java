package it.uniba.app.match;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ConsoleUtils;
import it.uniba.app.utils.InputChecker;
import it.uniba.app.utils.UserInput;

public class MatchController {
    private final UserInterface ui;

    private final Match match;

    public MatchController(UserInterface uiObj, Match matchObj) {
        this.match = matchObj;
        this.ui = uiObj;
    }

    /**
     * Inizia la partita
     */
    public void start() {
        match.setInProgress(true);

        UserInterface.printer.getGrid(match.getGuesses());

        update();
    }

    /**
     * Si occupa dell'aggiornamento della partita ad ogni input
     */
    private void update() {
        while (match.getCurrentGuessCtr() < Match.NUM_OF_GUESSES && match.isInProgress()) {
            dispatchInput(UserInput.get());
        }

        if (match.getCurrentGuessCtr() == 6 && match.isInProgress()) {
            UserInterface.printer.getMaxTriesReached(match.getSecretWord().getString());
        } else if (match.getGuess(match.getCurrentGuessCtr()).isCorrect()) {
            UserInterface.printer.getCorrectGuessNotification(match.getCurrentGuessCtr());
        } else {
            UserInterface.printer.getLeftCorrectlyNotification();
        }

    }

    private void dispatchInput(String userInput) {
        if (InputChecker.isCommand(userInput)) {
            ui.getCommands(userInput);
        } else {

            if (InputChecker.isValidAsWord(userInput)) {
                updateMatchStatus(guessAttempt(userInput));
                ConsoleUtils.clearScreen();
                UserInterface.printer.getGrid(match.getGuesses());
            } else if (userInput.length() < 5) {
                UserInterface.printer.getIncompleteGuess();
            } else if (userInput.length() > 5) {
                UserInterface.printer.getExcessiveGuess();
            } else {
                UserInterface.printer.getInvalidGuess();
            }
        }

    }

    private boolean guessAttempt(String userInput) {
        Guess guess = match.getGuess(match.getCurrentGuessCtr());
        guess.getController().examineGuessAttempt(userInput, match.getSecretWord());
        return guess.isCorrect();
    }

    private void updateMatchStatus(boolean guessCorrect) {
        if (guessCorrect) {
            match.setInProgress(false);
        } else {
            match.incrementCurrentGuessCtr();
        }
    }
}
