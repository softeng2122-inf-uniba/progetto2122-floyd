package it.uniba.app.ui.control;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ExecutableTaskGeneric;
import it.uniba.app.utils.UserInput;

public class ConfirmationRequester implements ExecutableTaskGeneric<Boolean> {

    @Override
    public Boolean execute(String str) {
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
