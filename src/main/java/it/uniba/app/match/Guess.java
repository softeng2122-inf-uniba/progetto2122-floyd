package it.uniba.app.match;

import it.uniba.app.utils.Resettable;

/**
 * {@literal <<entity>>}
 * Represents the guess attempt the user takes.
 * <p>
 * It's composed of a certain number of cells
 * given by {@link Match#NUM_OF_CELLS}.
 * <p>
 * Managed by a controller.
 *
 * @see Cell
 * @see it.uniba.app.match.controller.GuessController
 */
public class Guess implements Resettable {
    /** The chosen word from the user for the guess attempt. */
    private String chosenWord;

     /** True if the guess attempt is correct, false otherwise. */
    private boolean correct;

    /** Array of cells, representing each one a character of the chosen word. */
    public Cell[] cells;

    /**
     * Initializes a new Guess,
     * representing a sequence of empty cells (characters).
     */
    public Guess() {
        this.chosenWord = " ";
        this.correct = false;

        this.cells = new Cell[Match.NUM_OF_CELLS];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell();
        }
    }

    /**
     * Copy constructor. Instantiates a copied guess.
     *
     * @param guess the guess to copy.
     */
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
     * made on this object, see {@link #setCells}
     *
     * @return a deep copy of the cells (array).
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
     *
     * @param newCells the cells you want to save.
     */
    public void setCells(final Cell[] newCells) {
        Cell[] cellsCopy = new Cell[Match.NUM_OF_CELLS];
        for (int i = 0; i < cellsCopy.length; i++) {
            cellsCopy[i] = new Cell(newCells[i]);
            this.cells[i] = cellsCopy[i];
        }
    }
    
    /**
     * @param cellIdx index of the cell
     * @return a string representing ANSI sequence of the cell color.
     */
    public String getCellColor(final int cellIdx) {
        return cells[cellIdx].getColor();
    }

    /**
     * @param cellIdx index of the cell
     * @return a char representing the cell's character.
     */
    public char getCellCharacter(final int cellIdx) {
        return cells[cellIdx].getCharacter();
    }

    /**
     * @return {@code true} if the guess is correct, {@code false} otherwise.
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     * Sets correctness of the guess.
     *
     * @param value {@code true} if the guess is correct,
     *              {@code false} otherwise.
     */
    public void setCorrect(final boolean value) {
        this.correct = value;
    }

    /**
     * @return the chosen word for the guess attempt.
     */
    public String getChosenWord() {
        return chosenWord;
    }

      /**
     * Sets the chosen word for the guess attempt.
     *
     * @param userInput the string taken in input from user.
     */
    public void setChosenWord(final String chosenWord) {
        this.chosenWord = chosenWord;
    }

    /**
     * Resets the guess and its cells
     * to default state.
     */
    public void reset() {
        this.chosenWord = " ";
        this.correct = false;

        for (Cell cell : cells) {
            cell.reset();
        }
    }
}
