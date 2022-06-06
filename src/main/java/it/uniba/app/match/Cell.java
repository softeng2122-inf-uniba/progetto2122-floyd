package it.uniba.app.match;

import it.uniba.app.utils.Colors;

/**
 * <<Entity>> Elemento composto da un colore e una lettera.
 */
public class Cell {
    private String color;

    private char character;

    public Cell() {
        this.setColor(Colors.ANSI_WHITE_BACKGROUND);
        this.setCharacter(' ');
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
}
