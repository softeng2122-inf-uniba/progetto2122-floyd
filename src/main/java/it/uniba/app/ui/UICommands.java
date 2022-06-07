package it.uniba.app.ui;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.match.controller.SecretWordSetter;
import it.uniba.app.user.Player;
import it.uniba.app.utils.ConsoleUtils;
import it.uniba.app.utils.UserInput;

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
        if (matchController.isInProgress()) {
            UserInterface.printer.getLeaveRequestConfirmation();
            if (getConfirmation()) {
                matchController.endMatch();
            }
        } else {
            UserInterface.printer.getNoMatchToLeave();
        }
    }

    private boolean getConfirmation() {
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

    public void exit() {
        UserInterface.printer.getExitRequestConfirmation();
        if (getConfirmation()) {
            System.exit(0);
        }
    }

    public void showSecretWord(String lastSecretWord) {
        if (lastSecretWord == null) {
            UserInterface.printer.getSecretWordMissing();
        } else {
            UserInterface.printer.getShowSecretWord(lastSecretWord);
        }
    }

    public void help(Player user) {
        UserInterface.printer.getHelp(user);
    }

    public void newSecretWord(UserInterface ui, String secretWord, MatchController matchController) {
        new SecretWordSetter(ui, matchController).execute(secretWord);
    }

}
