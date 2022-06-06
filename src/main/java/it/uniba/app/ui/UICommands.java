package it.uniba.app.ui;

import it.uniba.app.match.Match;
import it.uniba.app.user.Player;
import it.uniba.app.user.Wordsmith;
import it.uniba.app.utils.UserInput;

public class UICommands {
    public UICommands() {
    }

    public void help(Player user) {
        Wordsmith.showHelp();
    }

    public void play(String lastSecretWord, Match match) {
        if (match.getIsInProgress()) {
            UserInterface.printer.getMatchAlreadyStarted();
        } else {
            if (lastSecretWord == null) {
                UserInterface.printer.getSecretWordMissing();
            } else {
                match.setSecretWord(lastSecretWord);
                match.start();
            }
        }
    }

    public void leave(Match match) {
        if (match.getIsInProgress()) {
            UserInterface.printer.getLeaveRequestConfirmation();
            String answer = UserInput.get();
            if (answer.equals("y")) {
                match.setIsInProgress(false);
            } else if (answer.equals("n")) {
            } else {
                UserInterface.printer.getInvalidOption();
            }
        } else {
            UserInterface.printer.getNoMatchToLeave();
        }
    }

    public void exit() {
        UserInterface.printer.getExitRequestConfirmation();
        String answer = UserInput.get();
        if (answer.equals("y")) {
            System.exit(0);
        } else if (answer.equals("n")) {
        } else {
            UserInterface.printer.getInvalidOption();
        }
    }

    public void showSecretWord(String lastSecretWord) {
        if (lastSecretWord == null)
            UserInterface.printer.getSecretWordMissing();
        else
            UserInterface.printer.getShowSecretWord(lastSecretWord);
    }

    public void newSecretWord(UserInterface ui, String secretWord, Match match) {
        if (UserInput.isValidAsWord(secretWord)) {
            if (match.getIsInProgress()) {
                match.setSecretWord(secretWord);
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
