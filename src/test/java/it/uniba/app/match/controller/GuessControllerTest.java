package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
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

    @Test
    public void testReset() {
        objToTest.startExamination("prova");
        assertTrue(objToTest.isCorrect());
        assertEquals('p', objToTest.getCellCharacter(0));
        assertEquals(ConsoleUtils.ANSI_GREEN_BACKGROUND, objToTest.getCellColor(0));
        objToTest.reset();
        assertFalse(objToTest.isCorrect());
        assertEquals(' ', objToTest.getCellCharacter(0));
        assertEquals(ConsoleUtils.ANSI_WHITE_BACKGROUND, objToTest.getCellColor(0));
    }
}
