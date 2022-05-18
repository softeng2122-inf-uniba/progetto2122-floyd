package it.uniba.app;

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

        this.cell = new Cell[5];
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
}
