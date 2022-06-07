package it.uniba.app.ui.control;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ConsoleUtils;
import it.uniba.app.utils.ExecutableTaskString;

public class PlayRequestProcessor implements ExecutableTaskString {

    private MatchController matchController;

    public PlayRequestProcessor(MatchController matchControllerObj) {
        this.matchController = matchControllerObj;
    }

    @Override
    public void execute(String lastSecretWord) {
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
