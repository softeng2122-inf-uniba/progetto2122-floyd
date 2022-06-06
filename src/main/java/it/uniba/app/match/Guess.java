package it.uniba.app.match;

import static it.uniba.app.utils.ConsoleUtils.ANSI_GREEN_BACKGROUND;
import static it.uniba.app.utils.ConsoleUtils.ANSI_WHITE_BACKGROUND;
import static it.uniba.app.utils.ConsoleUtils.ANSI_YELLOW_BACKGROUND;

/**
 * < < Entity > > Si occupa di controllare la validità del tentativo
 */
public class Guess {
    private String chosenWord;

    private boolean correct;

    public Cell[] cells;

    public Guess() {
        this.chosenWord = " ";
        this.correct = false;

        this.cells = new Cell[Match.NUM_OF_CELLS];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell();
        }
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setChosenWord(String chosenWord) {
        this.chosenWord = chosenWord;
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
        for (int i = 0; i < chosenWord.length(); i++) {
            cells[i].setCharacter(chosenWord.charAt(i));
            if (cells[i].getCharacter() == secretWord.getString().charAt(i)) {
                cells[i].setColor(ANSI_GREEN_BACKGROUND);
                secretWord.setMarked(i);
            }
        }
        // Poi controlla tutte le altre
        for (int i = 0; i < chosenWord.length(); i++) {
            for (int j = 0; j < secretWord.getString().length(); j++) {
                // Consideriamo per efficienza solo le celle non colorate
                if (cells[i].getColor() == ANSI_WHITE_BACKGROUND) {
                    if (!secretWord.isMarked(j)) {
                        if (cells[i].getCharacter() == secretWord.getString().charAt(j)) {
                            cells[i].setColor(ANSI_YELLOW_BACKGROUND);
                            secretWord.setMarked(j);
                        }
                    }
                }
            }
        }

        if (chosenWord.equals(secretWord.getString())) {
            correct = true;
            return true;
        } else
            return false;
    }
}
