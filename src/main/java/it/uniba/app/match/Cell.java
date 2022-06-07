package it.uniba.app.match;

import static it.uniba.app.utils.ConsoleUtils.ANSI_WHITE_BACKGROUND;

import it.uniba.app.utils.Resettable;

/**
 * <<Entity>> Elemento composto da un colore e una lettera.
 */
public class Cell implements Resettable {
    private String color;

    private char character;

    public Cell() {
        reset();
    }

    public Cell(final Cell cell) {
        this.color = cell.color;
        this.character = cell.character;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public void reset() {
        this.setColor(ANSI_WHITE_BACKGROUND);
        this.setCharacter(' ');
    }
}
