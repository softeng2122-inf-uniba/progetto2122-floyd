package it.uniba.app.ui.control;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ConsoleUtils;
import it.uniba.app.utils.ExecutableTaskString;

/**
 * Tries to start a match, if not already started.
 * Prints error otherwise.
 */
public class PlayRequestProcessor implements ExecutableTaskString {

    /** Reference to the match controller. */
    private MatchController matchController;

    /**
     * @param matchControllerObj the reference to the match controller.
     */
    public PlayRequestProcessor(MatchController matchControllerObj) {
        this.matchController = matchControllerObj;
    }

    /**
     * @param lastSecretWord the latest secret word.
     *                       Can be {@code null}.
     */
    @Override
    public void execute(final String lastSecretWord) {
        if (matchController.isInProgress()) {
            UserInterface.printer.getMatchAlreadyStarted();
        } else {
            if (lastSecretWord == null) {
                UserInterface.printer.getSecretWordMissing();
            } else {
                ConsoleUtils.clearScreen();
                matchController.start(lastSecretWord);
            }
        }
    }

}
