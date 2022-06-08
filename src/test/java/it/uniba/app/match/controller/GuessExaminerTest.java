package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.Guess;
import it.uniba.app.match.Match;
import it.uniba.app.match.Word;
import it.uniba.app.utils.ConsoleUtils;

public class GuessExaminerTest {
    private Guess guess;
    private Word secretWord;

    @BeforeEach
    public void setUp() {
        guess = new Guess();
        secretWord = new Word();
        secretWord.setString("prova");
    }

    @Test
    public void testExecute_CorrectGuess() {
        new GuessExaminer(guess, secretWord).execute("prova");

        for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
            assertEquals(guess.getCellCharacter(i), secretWord.getString().charAt(i));
            assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND, guess.getCellColor(i));
        }
        assertTrue(guess.isCorrect());
    }

    @Test
    public void testExecute_CompletelyIncorrectGuess() {
        new GuessExaminer(guess, secretWord).execute("libbe");

        for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
            assertEquals(guess.getCellCharacter(i), "libbe".charAt(i));
            assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND, guess.getCellColor(i));
        }
        assertFalse(guess.isCorrect());
    }

    @Test
    public void testExecute_AllLettersYellow() {
        new GuessExaminer(guess, secretWord).execute("avrop");

        for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
            assertEquals(guess.getCellCharacter(i), "avrop".charAt(i));
            assertEquals(ConsoleUtils.ANSI_YELLOW_BACKGROUND, guess.getCellColor(i));
        }
        assertFalse(guess.isCorrect());
    }

    @Test
    public void testExecute_SomeLettersYellowGreenWhite() {
        new GuessExaminer(guess, secretWord).execute("bravo");

        for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
            assertEquals(guess.getCellCharacter(i), "bravo".charAt(i));
        }
        assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND, guess.getCellColor(0));
        assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND, guess.getCellColor(1));
        assertEquals(ConsoleUtils.ANSI_YELLOW_BACKGROUND, guess.getCellColor(2));
        assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND, guess.getCellColor(3));
        assertEquals(ConsoleUtils.ANSI_YELLOW_BACKGROUND, guess.getCellColor(4));
        assertFalse(guess.isCorrect());
    }

    @Test
    public void testExecute_DoubleLetters() {
        new GuessExaminer(guess, secretWord).execute("brraa");

        for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
            assertEquals(guess.getCellCharacter(i), "brraa".charAt(i));
        }
        assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND, guess.getCellColor(0));
        assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND, guess.getCellColor(1));
        assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND, guess.getCellColor(2));
        assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND, guess.getCellColor(3));
        assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND, guess.getCellColor(4));
        assertFalse(guess.isCorrect());
    }
}
