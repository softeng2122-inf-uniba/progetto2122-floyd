package it.uniba.app.ui;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.match.controller.SecretWordSetter;
import it.uniba.app.ui.control.ExitRequestProcessor;
import it.uniba.app.ui.control.LeaveRequestProcessor;
import it.uniba.app.ui.control.SecretWordPrinter;
import it.uniba.app.user.UserController;
import it.uniba.app.utils.ConsoleUtils;

public class UICommands {
    public UICommands() {
    }

    public void play(String lastSecretWord, MatchController matchController) {
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

    public void leave(MatchController matchController) {
        new LeaveRequestProcessor(matchController).execute(null);
    }

    public void exit() {
        new ExitRequestProcessor().execute(null);
    }

    public void showSecretWord(String lastSecretWord) {
        new SecretWordPrinter().execute(lastSecretWord);
    }

    public void help(UserController userController) {
        UserInterface.printer.getHelp(userController);
    }

    public void newSecretWord(UserInterface ui, String secretWord, MatchController matchController) {
        new SecretWordSetter(ui, matchController).execute(secretWord);
    }

}
