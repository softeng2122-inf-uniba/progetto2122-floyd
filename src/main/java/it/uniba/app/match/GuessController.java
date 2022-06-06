package it.uniba.app.match;

import static it.uniba.app.utils.ConsoleUtils.ANSI_GREEN_BACKGROUND;
import static it.uniba.app.utils.ConsoleUtils.ANSI_WHITE_BACKGROUND;
import static it.uniba.app.utils.ConsoleUtils.ANSI_YELLOW_BACKGROUND;

public class GuessController {
    private final Guess guess;

    public GuessController(final Guess guessObj) {
        this.guess = guessObj;
    }

    /**
     * Si occupa di controllare lettera per lettera nella griglia la parola appena
     * inserita
     * 
     * @param secretWord Parola segreta della partita
     * @return
     */
    public void examineGuessAttempt(String userInput, Word matchSecretWord) {
        guess.setChosenWord(userInput);
        updateCellsCharacters();
        updateCellsColor(matchSecretWord);
        updateGuessStatus(matchSecretWord.getString());
    }

    private void updateCellsCharacters() {
        if (!guess.getChosenWord().equals(" ")) {
            Cell[] guessCells = guess.getCells();
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                guessCells[i].setCharacter(guess.getChosenWord().charAt(i));
            }
        }
    }

    private void updateCellsColor(Word secretWord) {
        Cell[] cells = guess.getCells();

        for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
            if (cells[i].getCharacter() == secretWord.getString().charAt(i)) {
                cells[i].setColor(ANSI_GREEN_BACKGROUND);
                secretWord.setMarked(i);
            }
        }

        for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
            if (cells[i].getColor().equals(ANSI_WHITE_BACKGROUND)) {
                for (int j = 0; j < Match.NUM_OF_CELLS; j++) {
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
    }

    private void updateGuessStatus(String secretWordString) {
        if (guess.getChosenWord().equals(secretWordString)) {
            guess.setCorrect(true);
        }
    }
}
