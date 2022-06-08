package it.uniba.app.ui.control;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.GenericExecutableTask;
import it.uniba.app.utils.UserInput;

/**
 *  @{literal <<control>>}
 * Asks for a confirmation.
 * The user is asked for a Y or N answer
 * (lower case accepted too).
 * If the given answer is invalid, prints error.
 */
public class ConfirmationRequester implements GenericExecutableTask<Boolean> {

     /** Instantiates a ConfirmationRequester task. */
    public ConfirmationRequester() {
    }

    /**
     * @return {@code true} if answer is yes,
     *         {@code false} otherwise.
     */
    @Override
    public Boolean execute() {
        String answer = UserInput.get();
        if (answer.equals("y")) {
            return true;
        } else if (answer.equals("n")) {
            return false;
        } else {
            UserInterface.printer.getInvalidOption();
            return false;
        }
    }

}
