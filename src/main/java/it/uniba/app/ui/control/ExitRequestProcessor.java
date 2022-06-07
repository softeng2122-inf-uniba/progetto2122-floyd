package it.uniba.app.ui.control;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ExecutableTask;

/** Exits the application, after a confirmation prompt. */
public class ExitRequestProcessor implements ExecutableTask {

    /** Instantiates a ExitRequest task. */
    public ExitRequestProcessor() {
    }

    @Override
    public void execute() {
        UserInterface.printer.getExitRequestConfirmation();
        if (new ConfirmationRequester().execute()) {
            System.exit(0);
        }
    }

}