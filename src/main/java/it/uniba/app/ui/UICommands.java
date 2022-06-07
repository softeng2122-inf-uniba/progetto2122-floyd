package it.uniba.app.ui;

import it.uniba.app.match.Match;
import it.uniba.app.match.controller.MatchController;
import it.uniba.app.user.Player;
import it.uniba.app.utils.ConsoleUtils;
import it.uniba.app.utils.InputChecker;
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
                matchController.setSecretWord(lastSecretWord);
                matchController.start();
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
        if (InputChecker.isValidAsWord(secretWord)) {
            if (matchController.isInProgress()) {
                matchController.setSecretWord(secretWord);
            }
            ui.setLastSecretWord(secretWord);
            UserInterface.printer.getOk();
        } else {
            if (secretWord.length() < Match.NUM_OF_CELLS) {
                UserInterface.printer.getSecretWordTooShort();
            } else if (secretWord.length() > Match.NUM_OF_CELLS) {
                UserInterface.printer.getSecretWordTooLong();
            } else {
                UserInterface.printer.getInvalidSecretWord();
            }

        }
    }

}
