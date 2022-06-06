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

    public void leave(Match match) {
        if (match.getIsInProgress()) {
            System.out.print("Sicuro di voler abbandonare la partita? Y/N: ");
            String answer = UserInput.get();
            if (answer.equals("y")) {
                match.setIsInProgress(false);
            } else if (answer.equals("n")) {
            } else {
                System.out.println("Inserire un'opzione valida!");
            }
        } else {
            System.out.println("Non è in corso alcuna partita da abbandonare.");
        }
    }

}
