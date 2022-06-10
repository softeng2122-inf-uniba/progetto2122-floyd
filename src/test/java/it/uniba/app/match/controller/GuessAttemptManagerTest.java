package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;

/** Test GuessAttemptManager class. */

public final class GuessAttemptManagerTest {

    /** Match object. */
    private Match match;

    /** UserInterface object. */
    private UserInterface ui;

    /** Setups the test. */
    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        match = new Match(ui);
        match.setSecretWord("prova");
    }

    /**
     * Test the execute method with a correct guess in input.
     * Checks if it returns true, since the guess is correct.
     */
    @Test
    public void testExecute_CorrectGuess() {
        assertTrue(
                new GuessAttemptManager(match).execute("prova"));
    }

    /**
     * Test the execute method with an incorrect guess in input.
     * Checks if it returns false, since the guess is incorrect.
     */
    @Test
    public void testExecute_IncorrectGuess() {
        assertFalse(
                new GuessAttemptManager(match).execute("papap"));
    }
}
