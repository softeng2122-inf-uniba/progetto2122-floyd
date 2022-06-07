package it.uniba.app.match.controller;

import static it.uniba.app.utils.ConsoleUtils.ANSI_GREEN_BACKGROUND;
import static it.uniba.app.utils.ConsoleUtils.ANSI_WHITE_BACKGROUND;
import static it.uniba.app.utils.ConsoleUtils.ANSI_YELLOW_BACKGROUND;

import it.uniba.app.match.Cell;
import it.uniba.app.match.Guess;
import it.uniba.app.match.Word;
import it.uniba.app.utils.ExecutableTask;

public class GuessExaminer implements ExecutableTask {

    private final Guess guess;

    private final Word secretWord;

    public GuessExaminer(final Guess guessObj, final Word secretWordObj) {
        this.guess = guessObj;
        this.secretWord = secretWordObj;
    }

    @Override
    public void execute(String userInput) {
        guess.setChosenWord(userInput);
        updateCellsCharacters();
        updateCellsColor();
        updateGuessStatus();
    }

    private void updateCellsCharacters() {
        if (!guess.getChosenWord().equals(" ")) {
            Cell[] guessCells = guess.getCells();
            for (int i = 0; i < guessCells.length; i++) {
                guessCells[i].setCharacter(guess.getChosenWord().charAt(i));
            }
            guess.setCells(guessCells);
        }
    }

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

    private void updateGuessStatus() {
        if (guess.getChosenWord().equals(secretWord.getString())) {
            guess.setCorrect(true);
        }
    }

}
