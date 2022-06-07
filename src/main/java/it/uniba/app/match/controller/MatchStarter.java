package it.uniba.app.match.controller;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ExecutableTask;
import it.uniba.app.utils.UserInput;

public class MatchStarter implements ExecutableTask {

    private Match match;
    private UserInterface ui;

    public MatchStarter(final Match matchObj, final UserInterface uiObj) {
        this.match = matchObj;
        this.ui = uiObj;
    }

    @Override
    public void execute(String str) {
        match.setInProgress(true);

        UserInterface.printer.getGrid(match.getGuessControllers());

        while (match.getCurrentGuessCtr() < Match.NUM_OF_GUESSES && match.isInProgress()) {
            new MatchInputDispatcher(match, ui).execute(UserInput.get());
        }

    }

}
