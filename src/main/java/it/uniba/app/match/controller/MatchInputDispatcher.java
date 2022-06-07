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
                Boolean guessAttempt = new GuessAttemptManager(match).execute(userInput);
                new MatchStatusUpdater(match).execute(guessAttempt.toString());
                ConsoleUtils.clearScreen();
                UserInterface.printer.getGrid(match.getGuesses());
            } else {
                UserInterface.printer.getGuessError(userInput);
            }
        }
    }
}
