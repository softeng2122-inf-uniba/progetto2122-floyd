package it.uniba.app.match;

import it.uniba.app.utils.Resettable;

/**
 * < < Entity > > Si occupa di controllare la validità del tentativo
 */
public class Guess implements Resettable {
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

    public Guess(final Guess guess) {
        this.chosenWord = guess.chosenWord;
        this.correct = guess.correct;
        this.cells = new Cell[Match.NUM_OF_CELLS];
        for (int i = 0; i < cells.length; i++) {
            this.cells[i] = guess.cells[i];
        }
    }

    /**
     * This will not return a reference to the
     * real object but a clone of the requested object.
     * It preserves the internal state of the object
     * from unintentional changes.
     * If you want to intentionally save the changes
     * made on this object, see setCells.
     */
    public Cell[] getCells() {
        Cell[] cellsCopy = new Cell[Match.NUM_OF_CELLS];
        for (int i = 0; i < cellsCopy.length; i++) {
            cellsCopy[i] = new Cell(this.cells[i]);
        }
        return cellsCopy;
    }

    /**
     * Updates the cells (array) by making a new copy, so that
     * it decouples the parameter from the actual new cells.
     * Use this when you want to save the changes made
     * on a deep copy.
     * It preserves the internal state of the object
     * from unintentional changes.
     */
    public void setCells(final Cell[] newCells) {
        Cell[] cellsCopy = new Cell[Match.NUM_OF_CELLS];
        for (int i = 0; i < cellsCopy.length; i++) {
            cellsCopy[i] = new Cell(newCells[i]);
            this.cells[i] = cellsCopy[i];
        }
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

    public String getChosenWord() {
        return chosenWord;
    }

    public void setChosenWord(String chosenWord) {
        this.chosenWord = chosenWord;
    }

    public void reset() {
        this.chosenWord = " ";
        this.correct = false;

        for (Cell cell : cells) {
            cell.reset();
        }
    }
}
