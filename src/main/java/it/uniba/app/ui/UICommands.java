package it.uniba.app.ui;

import it.uniba.app.user.Player;
import it.uniba.app.user.Wordsmith;

public class UICommands {
    public UICommands() {
    }

    public void help(Player user) {
        Wordsmith.showHelp();
    }

}
