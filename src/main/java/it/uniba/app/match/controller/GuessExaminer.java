package it.uniba.app.match.controller;

import static it.uniba.app.utils.ConsoleUtils.ANSI_GREEN_BACKGROUND;
import static it.uniba.app.utils.ConsoleUtils.ANSI_WHITE_BACKGROUND;
import static it.uniba.app.utils.ConsoleUtils.ANSI_YELLOW_BACKGROUND;

import it.uniba.app.match.Cell;
import it.uniba.app.match.Guess;
import it.uniba.app.match.Word;
import it.uniba.app.utils.ExecutableTaskString;

/**
 * {@literal <<control>>}
 * Holds methods needed for the examination of the attempt.
 */
public class GuessExaminer implements ExecutableTaskString {

    /** Reference to the guess object to examine. */
    private final Guess guess;

    /** Reference to the secret word object this attempt refers to. */
    private final Word secretWord;

    /**
     * @param guessObj      a reference to the guess object to examine.
     * @param secretWordObj a reference to the secret word object this attempt
     *                      refers to.
     */
    public GuessExaminer(final Guess guessObj, final Word secretWordObj) {
        this.guess = guessObj;
        this.secretWord = secretWordObj;
    }

    /**
     * Executes the examination of the attempt.
     * In detail, it stores the input,
     * transposes it into cells,
     * then checks and updates colors of the cells and finally
     * updates the guess status if it's correct.
     *
     * @param userInput the string taken from user input that should get
     *                  examined.
     */
    @Override
    public void execute(final String userInput) {
        guess.setChosenWord(userInput);
        updateCellsCharacters();
        updateCellsColor();
        updateGuessStatus();
    }

    /** Transposes the attempt characters to guess cells. */
    private void updateCellsCharacters() {
        Cell[] guessCells = guess.getCells();
        for (int i = 0; i < guessCells.length; i++) {
            guessCells[i].setCharacter(guess.getChosenWord().charAt(i));
        }
        guess.setCells(guessCells);
    }

    /**
     * Checks each letter of the guess,
     * setting the correct background color for each cell.
     */
    private void updateCellsColor() {
        Cell[] cells = guess.getCells();

        for (int i = 0; i < cells.length; i++) {
            if (cells[i].getCharacter() == secretWord.getString().charAt(i)) {
                cells[i].setColor(ANSI_GREEN_BACKGROUND);
                secretWord.setMarked(i);
            }
        }

        for (int i = 0; i < cells.length; i++) {
            if (cells[i].getColor().equals(ANSI_WHITE_BACKGROUND)) {
                for (int j = 0; j < cells.length; j++) {
                    if (!secretWord.isMarked(j)) {
                        if (cells[i].getCharacter() == secretWord.getString().charAt(j)) {
                            cells[i].setColor(ANSI_YELLOW_BACKGROUND);
                            secretWord.setMarked(j);
                            break;
                        }
                    }
                }
            }
        }

        guess.setCells(cells);
    }

    /**
     * Flags the guess as correct if it is so.
     * In detail, it compares the stored chosen word for the guess
     * and the current secret word of the match.
     */
    private void updateGuessStatus() {
        if (guess.getChosenWord().equals(secretWord.getString())) {
            guess.setCorrect(true);
        }
    }

}
