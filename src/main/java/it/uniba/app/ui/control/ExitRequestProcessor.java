package it.uniba.app.ui.control;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ExecutableTask;

/**
 * ExitRequestProcessor
 */
public class ExitRequestProcessor implements ExecutableTask {

    public ExitRequestProcessor() {
    }

    @Override
    public void execute(String str) {
        UserInterface.printer.getExitRequestConfirmation();
        if (new ConfirmationRequester().execute(null)) {
            System.exit(0);
        }
    }

}