package it.uniba.app.match;

import static it.uniba.app.utils.ConsoleUtils.ANSI_GREEN_BACKGROUND;
import static it.uniba.app.utils.ConsoleUtils.ANSI_WHITE_BACKGROUND;
import static it.uniba.app.utils.ConsoleUtils.ANSI_YELLOW_BACKGROUND;

public class GuessController {
    private final Guess guess;

    public GuessController(final Guess guessObj) {
        this.guess = guessObj;
    }

    /**
     * Si occupa di controllare lettera per lettera nella griglia la parola appena
     * inserita
     * 
     * @param secretWord Parola segreta della partita
     * @return
     */
    public boolean checkGuess(Word secretWord) {
        // Prima controlla tutte le lettere nelle posizioni corrette
        for (int i = 0; i < guess.getChosenWord().length(); i++) {
            guess.getCells()[i].setCharacter(guess.getChosenWord().charAt(i));
            if (guess.getCells()[i].getCharacter() == secretWord.getString().charAt(i)) {
                guess.getCells()[i].setColor(ANSI_GREEN_BACKGROUND);
                secretWord.setMarked(i);
            }
        }
        // Poi controlla tutte le altre
        for (int i = 0; i < guess.getChosenWord().length(); i++) {
            for (int j = 0; j < secretWord.getString().length(); j++) {
                // Consideriamo per efficienza solo le celle non colorate
                if (guess.getCells()[i].getColor() == ANSI_WHITE_BACKGROUND) {
                    if (!secretWord.isMarked(j)) {
                        if (guess.getCells()[i].getCharacter() == secretWord.getString().charAt(j)) {
                            guess.getCells()[i].setColor(ANSI_YELLOW_BACKGROUND);
                            secretWord.setMarked(j);
                        }
                    }
                }
            }
        }

        if (guess.getChosenWord().equals(secretWord.getString())) {
            guess.setCorrect(true);
            return true;
        } else
            return false;
    }

}
