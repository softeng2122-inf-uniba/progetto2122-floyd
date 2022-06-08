package it.uniba.app.match.controller;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ExecutableTask;
import it.uniba.app.utils.UserInput;
/**
 * {@literal <<control>>}
 * Responsible of starting the match and taking each input
 * during the match time.
 */
public class MatchStarter implements ExecutableTask {

    /** Reference to the match to start. */
    private Match match;

    /** Reference to the user interface object. */
    private UserInterface ui;

    /**
     * @param matchObj a reference to the match to start.
     * @param uiObj    a reference to the user interface object.
     */
    public MatchStarter(final Match matchObj, final UserInterface uiObj) {
        this.match = matchObj;
        this.ui = uiObj;
    }

    /**
     * Starts the match, prints an empty grid and then
     * proceeds to take inputs until the match is over.
     */
    @Override
    public void execute() {
        match.setInProgress(true);

        UserInterface.printer.getGrid(match.getGuessControllers());

        while (match.getCurrentGuessCtr() < Match.NUM_OF_GUESSES && match.isInProgress()) {
            new MatchInputDispatcher(match, ui).execute(UserInput.get());
        }

    }

}
