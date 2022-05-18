package it.uniba.app;

import it.uniba.utils.Colors;

/**
 * < < Entity > > Si occupa di controllare la validità del tentativo
 */
class Guess {
    private String chosenWord;

    private boolean isCorrect;

    Cell[] cell;

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
     * Si occupa di controllare lettera per lettera nella griglia la parola appena inserita
     * 
     * @param secretWord Parola segreta della partita
     * @return
     */
    public boolean checkGuess(Word secretWord) {
        // Prima controlla tutte le lettere nelle posizioni corrette
        for (int i = 0; i < chosenWord.length(); i++) {
            cell[i].setCharacter(chosenWord.charAt(i));
            if (cell[i].getCharacter() == secretWord.getWord().charAt(i)) {
                cell[i].setColor(Colors.ANSI_GREEN_BACKGROUND);
                secretWord.setAsMarked(i);
            }
        }
        // Poi controlla tutte le altre
        for (int i = 0; i < chosenWord.length(); i++) {
            for (int j = 0; j < secretWord.getWord().length(); j++) {
                // Consideriamo per efficienza solo le celle non colorate
                if (cell[i].getColor() == Colors.ANSI_WHITE_BACKGROUND) {
                    if (!secretWord.getIsMarked(j)) {
                        if (cell[i].getCharacter() == secretWord.getWord().charAt(j)) {
                            cell[i].setColor(Colors.ANSI_YELLOW_BACKGROUND);
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
