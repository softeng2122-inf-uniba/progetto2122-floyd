package it.uniba.app.ui.control;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.GenericExecutableTask;
import it.uniba.app.utils.UserInput;

public class ConfirmationRequester implements GenericExecutableTask<Boolean> {

    public ConfirmationRequester() {
    }

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
