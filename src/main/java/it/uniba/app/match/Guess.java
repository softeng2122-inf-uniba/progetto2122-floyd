package it.uniba.app.match;

import static it.uniba.app.utils.ConsoleUtils.ANSI_GREEN_BACKGROUND;
import static it.uniba.app.utils.ConsoleUtils.ANSI_WHITE_BACKGROUND;
import static it.uniba.app.utils.ConsoleUtils.ANSI_YELLOW_BACKGROUND;

/**
 * < < Entity > > Si occupa di controllare la validità del tentativo
 */
public class Guess {
    private String chosenWord;

    private boolean isCorrect;

    public Cell[] cell;

    public Guess() {
        this.chosenWord = " ";
        this.isCorrect = false;

        this.cell = new Cell[Match.NUM_OF_CELLS];
        for (int i = 0; i < cell.length; i++) {
            cell[i] = new Cell();
        }
    }

    public boolean getIsCorrect() {
        return isCorrect;
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
            cell[i].setCharacter(chosenWord.charAt(i));
            if (cell[i].getCharacter() == secretWord.getWord().charAt(i)) {
                cell[i].setColor(ANSI_GREEN_BACKGROUND);
                secretWord.setAsMarked(i);
            }
        }
        // Poi controlla tutte le altre
        for (int i = 0; i < chosenWord.length(); i++) {
            for (int j = 0; j < secretWord.getWord().length(); j++) {
                // Consideriamo per efficienza solo le celle non colorate
                if (cell[i].getColor() == ANSI_WHITE_BACKGROUND) {
                    if (!secretWord.getIsMarked(j)) {
                        if (cell[i].getCharacter() == secretWord.getWord().charAt(j)) {
                            cell[i].setColor(ANSI_YELLOW_BACKGROUND);
                            secretWord.setAsMarked(j);
                        }
                    }
                }
            }
        }

        if (chosenWord.equals(secretWord.getWord())) {
            isCorrect = true;
            return true;
        } else
            return false;
    }
}
