package it.uniba.app.match.controller;

import it.uniba.app.match.Match;
import it.uniba.app.utils.ExecutableTask;

public class MatchStatusUpdater implements ExecutableTask {
    private final Match match;

    public MatchStatusUpdater(final Match matchObj) {
        this.match = matchObj;
    }

    @Override
    public void execute(String guessCorrect) {
        if (Boolean.parseBoolean(guessCorrect)) {
            match.setInProgress(false);
        } else {
            match.incrementCurrentGuessCtr();
        }
    }

}
