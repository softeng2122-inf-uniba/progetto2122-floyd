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

    public Guess(final Guess guess) {
        this.chosenWord = guess.chosenWord;
        this.correct = guess.correct;
        this.cells = new Cell[Match.NUM_OF_CELLS];
        for (int i = 0; i < cells.length; i++) {
            this.cells[i] = guess.cells[i];
        }
        this.controller = new GuessController(this);
    }

    public Cell[] getCells() {
        Cell[] cellsCopy = new Cell[Match.NUM_OF_CELLS];
        for (int i = 0; i < cellsCopy.length; i++) {
            cellsCopy[i] = new Cell(this.cells[i]);
        }
        return cellsCopy;
    }

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

    public GuessController getController() {
        return controller;
    }

    public void initGuess() {
        this.chosenWord = " ";
        this.correct = false;

        for (Cell cell : cells) {
            cell.initCell();
        }
    }
}
