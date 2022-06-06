package it.uniba.app.match;

import static it.uniba.app.utils.ConsoleUtils.ANSI_WHITE_BACKGROUND;

/**
 * <<Entity>> Elemento composto da un colore e una lettera.
 */
public class Cell {
    private String color;

    private char character;

    public Cell() {
        initCell();
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

    public void initCell() {
        this.setColor(ANSI_WHITE_BACKGROUND);
        this.setCharacter(' ');
    }
}
