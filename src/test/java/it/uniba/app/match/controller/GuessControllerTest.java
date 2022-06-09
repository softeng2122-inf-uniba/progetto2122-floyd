package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.uniba.app.utils.ConsoleUtils;

public class GuessControllerTest {

    private GuessController objToTest;

    @BeforeEach
    public void setUp() {
        objToTest = new GuessController();
        objToTest.setSecretWordToGuess("prova");
    }

    @Test
    public void testStartExamination_Incorrect() {
        objToTest.startExamination("ciaoo");
        assertFalse(objToTest.isCorrect());
    }

    @Test
    public void testStartExamination_Correct() {
        objToTest.startExamination("prova");
        assertTrue(objToTest.isCorrect());
    }

    @Nested
    class ResetTest {
        @BeforeEach
        public void setUp() {
            objToTest.startExamination("prova");
        }

        @Test
        public void testReset_Before_IsCorrect() {
            assertTrue(objToTest.isCorrect());
        }

        @Test
        public void testReset_Before_CellContainsP() {
            assertEquals('p', objToTest.getCellCharacter(0));
        }

        @Test
        public void testReset_Before_CellIsGreen() {
            assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND, objToTest.getCellColor(0));
        }

        @Test
        public void testReset_After_IsNotCorrect() {
            objToTest.reset();
            assertFalse(objToTest.isCorrect());
        }

        @Test
        public void testReset_After_CellContainsWhitespace() {
            objToTest.reset();
            assertEquals(' ', objToTest.getCellCharacter(0));
        }

        @Test
        public void testReset_After_CellIsWhite() {
            objToTest.reset();
            assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND, objToTest.getCellColor(0));
        }
    }

}
