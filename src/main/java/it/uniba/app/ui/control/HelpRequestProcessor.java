package it.uniba.app.ui.control;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;
import it.uniba.app.utils.ExecutableTask;

public class HelpRequestProcessor implements ExecutableTask {

    private UserController userController;

    public HelpRequestProcessor(UserController userControllerObj) {
        this.userController = userControllerObj;
    }

    @Override
    public void execute(String str) {
        UserInterface.printer.getHelp(userController);
    }

}
