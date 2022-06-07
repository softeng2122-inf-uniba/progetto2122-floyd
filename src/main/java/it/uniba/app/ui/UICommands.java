package it.uniba.app.ui;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.ui.control.ExitRequestProcessor;
import it.uniba.app.ui.control.HelpRequestProcessor;
import it.uniba.app.ui.control.LeaveRequestProcessor;
import it.uniba.app.ui.control.NewSecretWordProcessor;
import it.uniba.app.ui.control.PlayRequestProcessor;
import it.uniba.app.ui.control.ShowSecretWordProcessor;
import it.uniba.app.user.UserController;

public class UICommands {
    public UICommands() {
    }

    public void play(String lastSecretWord, MatchController matchController) {
        new PlayRequestProcessor(matchController);
    }

    public void leave(MatchController matchController) {
        new LeaveRequestProcessor(matchController).execute(null);
    }

    public void exit() {
        new ExitRequestProcessor().execute(null);
    }

    public void showSecretWord(String lastSecretWord) {
        new ShowSecretWordProcessor().execute(lastSecretWord);
    }

    public void help(UserController userController) {
        new HelpRequestProcessor(userController).execute(null);
    }

    public void newSecretWord(UserInterface ui, String secretWord, MatchController matchController) {
        new NewSecretWordProcessor(ui, matchController).execute(secretWord);
    }

}
