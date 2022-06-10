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

/** Test GuessExaminer class. */
public final class GuessExaminerTest {
    /** Guess object. */
    private Guess guess;

    /** Word object. */
    private Word secretWord;

    /** Setups the test. */
    @BeforeEach
    public void setUp() {
        guess = new Guess();
        secretWord = new Word();
        secretWord.setString("prova");
    }

    /** Test execute method with a correct guess. */
    @Nested
    class TestExecute_CorrectGuess {
        /** Setups the test. */
        @BeforeEach
        public void setUp() {
            new GuessExaminer(guess, secretWord).execute("prova");
        }

        /** Tests that each cell has the correct character. */
        @Test
        public void testCellsCharacterAreOk() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(guess.getCellCharacter(i),
                        secretWord.getString().charAt(i));
            }
        }

        /** Tests that each cell has the correct color. */
        @Test
        public void testCellsAreGreen() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND,
                        guess.getCellColor(i));
            }
        }

        /** Tests that guess has been marked as correct. */
        @Test
        public void testGuessIsCorrect() {
            assertTrue(guess.isCorrect());
        }
    }

    /** Test execute method with a completely incorrect guess. */
    @Nested
    class TestExecute_CompletelyIncorrectGuess {
        /** Setups the test. */
        @BeforeEach
        public void setUp() {
            new GuessExaminer(guess, secretWord).execute("libbe");
        }

        /** Tests that each cell has the correct character. */
        @Test
        public void testCellCharactersAreOk() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(guess.getCellCharacter(i),
                        "libbe".charAt(i));
            }
        }

        /** Tests that each cell has the correct color. */
        @Test
        public void testCellsAreWhite() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND,
                        guess.getCellColor(i));
            }
        }

        /** Tests that guess has not been marked as correct. */
        @Test
        public void testGuessIsNotCorrect() {
            assertFalse(guess.isCorrect());
        }
    }

    /** Test execute method with a guess that has all the letters yellow. */
    @Nested
    class testExecute_AllLettersYellow {
        /** Setups the test. */
        @BeforeEach
        public void setUp() {
            new GuessExaminer(guess, secretWord).execute("avrop");
        }

        /** Tests that each cell has the correct character. */
        @Test
        public void testCellCharactersAreOk() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(guess.getCellCharacter(i),
                        "avrop".charAt(i));
            }
        }

        /** Tests that each cell has the correct color. */
        @Test
        public void testCellsAreYellow() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(ConsoleUtils.ANSI_YELLOW_BACKGROUND,
                        guess.getCellColor(i));
            }
        }

        /** Tests that guess has not been marked as correct. */
        @Test
        public void testGuessIsNotCorrect() {
            assertFalse(guess.isCorrect());
        }
    }

    /** Test execute method with a guess that has double letters. */
    @Nested
    class testExecute_DoubleLettersGuess {
        /** Setups the test. */
        @BeforeEach
        public void setUp() {
            new GuessExaminer(guess, secretWord).execute("brraa");
        }

        /** Tests that each cell has the correct character. */
        @Test
        public void testCellCharactersAreOk() {
            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(guess.getCellCharacter(i),
                        "brraa".charAt(i));
            }
        }

        /** Tests that each cell has the correct color. */
        @Test
        public void testCellColorsAreOk() {
            assertAll(
                    () -> assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND,
                            guess.getCellColor(0)),
                    () -> assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND,
                            guess.getCellColor(1)),
                    () -> assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND,
                            guess.getCellColor(2)),
                    () -> assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND,
                            guess.getCellColor(3)),
                    () -> assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND,
                            guess.getCellColor(4))

            );
        }

        /** Tests that guess has not been marked as correct. */
        @Test
        public void testGuessIsNotCorrect() {
            assertFalse(guess.isCorrect());
        }
    }

    /**
     * Test execute method with a guess that has double letters and
     * the secret word too.
     */
    @Nested
    class testExecute_DoubleLettersGuess_DoubleLettersSecretWord {
        /** Setups the test. */
        @BeforeEach
        public void setUp() {
            secretWord.setString("parra");
            new GuessExaminer(guess, secretWord).execute("brraa");
        }

        /** Tests that each cell has the correct character. */
        @Test
        public void testCellCharactersAreOk() {

            for (int i = 0; i < Match.NUM_OF_CELLS; i++) {
                assertEquals(guess.getCellCharacter(i),
                        "brraa".charAt(i));
            }
        }

        /** Tests that each cell has the correct color. */
        @Test
        public void testCellColorsAreOk() {
            assertAll(
                    () -> assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND,
                            guess.getCellColor(0)),
                    () -> assertEquals(ConsoleUtils.ANSI_YELLOW_BACKGROUND,
                            guess.getCellColor(1)),
                    () -> assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND,
                            guess.getCellColor(2)),
                    () -> assertEquals(ConsoleUtils.ANSI_YELLOW_BACKGROUND,
                            guess.getCellColor(3)),
                    () -> assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND,
                            guess.getCellColor(4))

            );

        }

        /** Tests that guess has not been marked as correct. */
        @Test
        public void testGuessIsNotCorrect() {
            assertFalse(guess.isCorrect());
        }
    }

}
