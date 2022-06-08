package it.uniba.app.ui.control;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;
import it.uniba.app.utils.ExecutableTask;

/** Prints the available commands for the user privileges. */
public final class HelpRequestProcessor implements ExecutableTask {

    /** the reference to the user controller. */
    private UserController userController;

    /**
     * @param userControllerObj the reference to the user controller.
     */
    public HelpRequestProcessor(final UserController userControllerObj) {
        this.userController = userControllerObj;
    }

    @Override
    public void execute() {
        UserInterface.printer.getHelp(userController);
    }

}
