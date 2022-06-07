package it.uniba.app.ui.control;

import it.uniba.app.match.Match;
import it.uniba.app.match.controller.MatchController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ExecutableTaskString;
import it.uniba.app.utils.InputChecker;

public class NewSecretWordProcessor implements ExecutableTaskString {
    private final MatchController matchController;

    private final UserInterface ui;

    public NewSecretWordProcessor(final UserInterface uiObj, final MatchController matchControllerObj) {
        this.ui = uiObj;
        this.matchController = matchControllerObj;
    }

    @Override
    public void execute(String input) {
        if (InputChecker.isValidAsWord(input)) {
            if (matchController.isInProgress()) {
                matchController.setSecretWord(input);
            }
            ui.setLastSecretWord(input);
            UserInterface.printer.getOk();
        } else {
            if (input.length() < Match.NUM_OF_CELLS) {
                UserInterface.printer.getSecretWordTooShort();
            } else if (input.length() > Match.NUM_OF_CELLS) {
                UserInterface.printer.getSecretWordTooLong();
            } else {
                UserInterface.printer.getInvalidSecretWord();
            }
        }
    }

}
