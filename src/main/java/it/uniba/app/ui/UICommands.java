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

    public void exit() {
        System.out.print("Sicuro di voler uscire dal gioco? Y/N: ");
        String answer = UserInput.get();
        if (answer.equals("y")) {
            System.exit(0);
        } else if (answer.equals("n")) {
        } else {
            System.out.println("Inserire un'opzione valida!");
        }
    }

    public void showSecretWord(String lastSecretWord) {
        if (lastSecretWord == null)
            System.out.println("Non è stata ancora impostata una parola segreta.");
        else
            System.out.println("La parola segreta è: " + lastSecretWord);
    }

    public void newSecretWord(UserInterface ui, String secretWord, Match match) {
        if (UserInput.isValidAsWord(secretWord)) {
            if (match.getIsInProgress()) {
                match.setSecretWord(secretWord);
            }
            ui.setLastSecretWord(secretWord);
            System.out.println("OK");
        } else {
            if (secretWord.length() < Match.NUM_OF_CELLS) {
                System.out.println("Parola segreta troppo corta");
            } else if (secretWord.length() > Match.NUM_OF_CELLS) {
                System.out.println("Parola segreta troppo lunga");
            } else {
                System.out.println("Parola segreta non valida!");
            }

        }
    }

}
