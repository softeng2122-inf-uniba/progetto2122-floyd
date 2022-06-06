package it.uniba.app.ui;

import it.uniba.app.match.Match;
import it.uniba.app.user.Player;
import it.uniba.app.user.Wordsmith;

public class UICommands {
    public UICommands() {
    }

    public void help(Player user) {
        Wordsmith.showHelp();
    }

    public void play(String lastSecretWord, Match match) {
        if (match.getIsInProgress()) {
            System.out.println("La partita è già in corso!");
        } else {
            if (lastSecretWord == null) {
                System.out.println("Parola segreta mancante");
            } else {
                match.setSecretWord(lastSecretWord);
                match.start();
            }
        }
    }

}
