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

    public void play(Player user, String lastSecretWord, Match match) {
        try {
            if (match.getIsInProgress()) {
                System.out.println("La partita è già in corso!");
            } else {
                if (lastSecretWord == null) {
                    System.out.println("Parola segreta mancante");
                } else {
                    match = new Match(user, lastSecretWord, this);
                    match.start();
                }
            }
        } catch (NullPointerException e) {
            if (lastSecretWord == null) {
                System.out.println("Parola segreta mancante");
            } else {
                match = new Match(user, lastSecretWord, this);
                match.start();
            }

        }
    }

}
