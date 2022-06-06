package it.uniba.app.match;

/**
 * < < Entity > > Si occupa di controllare la validità del tentativo
 */
public class Guess {
    private String chosenWord;

    private boolean correct;

    public Cell[] cells;

    private final GuessController controller;

    public Guess() {
        this.chosenWord = " ";
        this.correct = false;
        this.controller = new GuessController(this);

        this.cells = new Cell[Match.NUM_OF_CELLS];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell();
        }
    }

    public Cell[] getCells() {
        return cells;
    }

    public String getCellColor(int idx) {
        return cells[idx].getColor();
    }

    public char getCellCharacter(final int cellIdx) {
        return cells[cellIdx].getCharacter();
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean value) {
        this.correct = value;
    }

    public void setChosenWord(String chosenWord) {
        this.chosenWord = chosenWord;
    }

    public String getChosenWord() {
        return chosenWord;
    }

    public GuessController getController() {
        return controller;
    }
}
