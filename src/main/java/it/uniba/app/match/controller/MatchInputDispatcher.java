package it.uniba.app.match.controller;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ConsoleUtils;
import it.uniba.app.utils.ExecutableTaskString;
import it.uniba.app.utils.InputChecker;

/**
 * {@literal <<control>>}
 * Dispatches the user input during a match,
 * depending on whether the input is a command or a guess.
 */
public class MatchInputDispatcher implements ExecutableTaskString {

    /** Reference to the match currently in progress. */
    private final Match match;

    /** Reference to the user interface object. */
    private final UserInterface ui;

    /**
     * @param matchObj a reference to the match currently in progress.
     * @param uiObj    a reference to the user interface object.
     */
    public MatchInputDispatcher(final Match matchObj, final UserInterface uiObj) {
        this.match = matchObj;
        this.ui = uiObj;
    }

    /**
     * Dispatches the user input. If it's a command,
     * it gets dispatched to the UI, if it's a guess attempt,
     * the {@link GuessAttemptManager} and {@link MatchStatusUpdater}
     * will take the responsibility.
     *
     * @param userInput the string taken on input.
     */
    @Override
    public void execute(final String userInput) {
        if (InputChecker.isCommand(userInput)) {
            ui.getCommands(userInput);
        } else {
            if (InputChecker.isValidAsWord(userInput)) {
                Boolean guessAttempt = new GuessAttemptManager(match).execute(userInput);
                new MatchStatusUpdater(match).execute(guessAttempt.toString());
                ConsoleUtils.clearScreen();
                UserInterface.printer.getGrid(match.getGuessControllers());
            } else {
                UserInterface.printer.getGuessError(userInput);
            }
        }
    }
}
