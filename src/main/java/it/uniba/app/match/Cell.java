package it.uniba.app.match;

import static it.uniba.app.utils.ConsoleUtils.ANSI_WHITE_BACKGROUND;

import it.uniba.app.utils.Resettable;

/**
 * {@literal <<entity>>}
 * Represents the cell that compose the grid,
 * in which are going to be stored the letters of a guess
 * attempt and its colors.
 */

public class Cell implements Resettable {
    /**
     * Contains ANSI escape sequence for background color.
     *
     * @see it.uniba.app.utils.ConsoleUtils
     */
    private String color;
    /** Contains a character. */
    private char character;

    /**
     * Instantiates an empty cell.
     * Empty means white background and whitespace character.
     */
    public Cell() {
        reset();
    }
     /**
     * Copy constructor. Instantiates a copied cell.
     *
     * @param cell the cell to copy.
     */

    public Cell(final Cell cell) {
        this.color = cell.color;
        this.character = cell.character;
    }

    /**
     * @return ANSI escape sequence for background color.
     */
    public String getColor() {
        return color;
    }

    /**
     * @param ansi the ANSI escape sequence for
     *             the color background the cell should be.
     */

    public void setColor(final String color) {
        this.color = color;
    }

   /**
     * @return the character contained in the cell.
     */
    public char getCharacter() {
        return character;
    }

    /**
     * @param ch the character the cell must contain.
     */
    public void setCharacter(final char character) {
        this.character = character;
    }

    /**
     * Resets the cell to default state.
     * It means white background and whitespace character.
     */
    public void reset() {
        this.setColor(ANSI_WHITE_BACKGROUND);
        this.setCharacter(' ');
    }
}
