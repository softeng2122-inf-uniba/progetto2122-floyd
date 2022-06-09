package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.uniba.app.utils.ConsoleUtils;

/** Test GuessController class. */
public final class GuessControllerTest {

    /** Class to test. */
    private GuessController objToTest;

    /** Setups the test. */
    @BeforeEach
    public void setUp() {
        objToTest = new GuessController();
        objToTest.setSecretWordToGuess("prova");
    }

    /**
     * Test the startExamination method with an incorrect guess in input.
     * Checks if it marks the guess as correct since it's not.
     */
    @Test
    public void testStartExamination_Incorrect() {
        objToTest.startExamination("ciaoo");
        assertFalse(objToTest.isCorrect());
    }

    /**
     * Test the startExamination method with an correct guess in input.
     * Checks if it marks the guess as correct since it is.
     */
    @Test
    public void testStartExamination_Correct() {
        objToTest.startExamination("prova");
        assertTrue(objToTest.isCorrect());
    }

    /** Test the reset method. */
    @Nested
    class ResetTest {
        /** Setups the test. */
        @BeforeEach
        public void setUp() {
            objToTest.startExamination("prova");
        }

        /** Test isCorrect before the reset. */
        @Test
        public void testReset_Before_IsCorrect() {
            assertTrue(objToTest.isCorrect());
        }

        /** Test getCellCharacter before the reset. */
        @Test
        public void testReset_Before_CellContainsP() {
            assertEquals('p', objToTest.getCellCharacter(0));
        }


        /** Test getCellColor before the reset. */
        @Test
        public void testReset_Before_CellIsGreen() {
            assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND,
                    objToTest.getCellColor(0));
        }

        /** Test isCorrect after the reset. */
        @Test
        public void testReset_After_IsNotCorrect() {
            objToTest.reset();
            assertFalse(objToTest.isCorrect());
        }

        /** Test getCellCharacter after the reset. */
        @Test
        public void testReset_After_CellContainsWhitespace() {
            objToTest.reset();
            assertEquals(' ', objToTest.getCellCharacter(0));
        }

        /** Test getCellColor after the reset. */
        @Test
        public void testReset_After_CellIsWhite() {
            objToTest.reset();
            assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND,
                    objToTest.getCellColor(0));
        }
    }

}
