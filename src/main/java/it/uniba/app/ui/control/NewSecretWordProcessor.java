package it.uniba.app.ui.control;

import it.uniba.app.match.Match;
import it.uniba.app.match.controller.MatchController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ExecutableTaskString;
import it.uniba.app.utils.InputChecker;

/**
 * {@literal <<control>>}
 * This method tries to set a new secret word,
 * it checks if it's valid before and then
 * updates the old one with the new one.
 */
public final class NewSecretWordProcessor implements ExecutableTaskString {

    /** Reference to the match controller. */
    private final MatchController matchController;

    /** Reference to the user interface. */
    private final UserInterface ui;

    /**
     * @param uiObj              reference to the user interface.
     * @param matchControllerObj reference to the match controller.
     */
    public NewSecretWordProcessor(final UserInterface uiObj, final MatchController matchControllerObj) {
        this.ui = uiObj;
        this.matchController = matchControllerObj;
    }

    /**
     * @param input the new secret word.
     */
    public void execute(final String input) {
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
