package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.Guess;
import it.uniba.app.match.Match;
import it.uniba.app.match.Word;
import it.uniba.app.utils.ConsoleUtils;

public final class GuessExaminerTest {
    private Guess guess;
    private Word secretWord;

    @BeforeEach
    public void setUp() {
        guess = new Guess();
        secretWord = new Word();
        secretWord.setString("prova");
    }

    @Nested
    class TestExecute_CorrectGuess {
        @BeforeEach
        public void setUp() {
            new GuessExaminer(guess, secretWord).execute("prova");
        }

        @Test
        public void testCellsCharacterAreOk() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(guess.getCellCharacter(i), secretWord.getString().charAt(i));
            }
        }

        @Test
        public void testCellsAreGreen() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND, guess.getCellColor(i));
            }
        }

        @Test
        public void testGuessIsCorrect() {
            assertTrue(guess.isCorrect());
        }
    }

    @Nested
    class TestExecute_CompletelyIncorrectGuess {
        @BeforeEach
        public void setUp() {
            new GuessExaminer(guess, secretWord).execute("libbe");
        }

        @Test
        public void testCellCharactersAreOk() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(guess.getCellCharacter(i), "libbe".charAt(i));
            }
        }

        @Test
        public void testCellsAreWhite() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND, guess.getCellColor(i));
            }
        }

        @Test
        public void testGuessIsNotCorrect() {
            assertFalse(guess.isCorrect());
        }
    }

    @Nested
    class testExecute_AllLettersYellow {
        @BeforeEach
        public void setUp() {
            new GuessExaminer(guess, secretWord).execute("avrop");
        }

        @Test
        public void testCellCharactersAreOk() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(guess.getCellCharacter(i), "avrop".charAt(i));
            }
        }

        @Test
        public void testCellsAreYellow() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(ConsoleUtils.ANSI_YELLOW_BACKGROUND, guess.getCellColor(i));
            }
        }

        @Test
        public void testGuessIsNotCorrect() {
            assertFalse(guess.isCorrect());
        }
    }

    @Nested
    class testExecute_DoubleLettersGuess {
        @BeforeEach
        public void setUp() {
            new GuessExaminer(guess, secretWord).execute("brraa");
        }

        @Test
        public void testCellCharactersAreOk() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(guess.getCellCharacter(i), "brraa".charAt(i));
            }
        }

        @Test
        public void testCellColorsAreOk() {
            assertAll(
                    () -> assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND, guess.getCellColor(0)),
                    () -> assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND, guess.getCellColor(1)),
                    () -> assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND, guess.getCellColor(2)),
                    () -> assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND, guess.getCellColor(3)),
                    () -> assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND, guess.getCellColor(4))

            );
        }

        @Test
        public void testGuessIsNotCorrect() {
            assertFalse(guess.isCorrect());
        }
    }

    @Nested
    class testExecute_DoubleLettersGuess_DoubleLettersSecretWord {
        @BeforeEach
        public void setUp() {
            secretWord.setString("parra");
            new GuessExaminer(guess, secretWord).execute("brraa");
        }

        @Test
        public void testCellCharactersAreOk() {

            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(guess.getCellCharacter(i), "brraa".charAt(i));
            }
        }

        @Test
        public void testCellColorsAreOk() {
            assertAll(
                    () -> assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND, guess.getCellColor(0)),
                    () -> assertEquals(ConsoleUtils.ANSI_YELLOW_BACKGROUND, guess.getCellColor(1)),
                    () -> assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND, guess.getCellColor(2)),
                    () -> assertEquals(ConsoleUtils.ANSI_YELLOW_BACKGROUND, guess.getCellColor(3)),
                    () -> assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND, guess.getCellColor(4))

            );

        }

        @Test
        public void testGuessIsNotCorrect() {
            assertFalse(guess.isCorrect());
        }
    }

}
