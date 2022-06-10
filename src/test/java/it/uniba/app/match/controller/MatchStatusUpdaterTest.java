package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;

/** Test MatchStatusUpdater class. */
public final class MatchStatusUpdaterTest {
    /** Match object. */
    private Match match;

    /** UserInterface object. */
    private UserInterface ui;

    /** Setups the test. */
    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        match = new Match(ui);
        match.setInProgress(true);
    }

    /**
     * Test execute with a "true" guess correct.
     * Checks if the match is still in progress.
     */
    @Test
    public void testExecute_CorrectGuess() {
        new MatchStatusUpdater(match).execute("true");
        assertFalse(match.isInProgress());
    }

    /**
     * Test execute with a "false" guess correct.
     * Checks if the match is still in progress.
     */
    @Test
    public void testExecute_IncorrectGuess_MatchIsInProgress() {
        new MatchStatusUpdater(match).execute("false");
        assertTrue(match.isInProgress());
    }

    /**
     * Test execute with a "false" guess correct.
     * Checks if the guess counter increments.
     */
    @Test
    public void testExecute_IncorrectGuess_MatchGuessCounterIncrements() {
        int oldCtr = match.getCurrentGuessCtr();
        new MatchStatusUpdater(match).execute("false");
        assertEquals((oldCtr + 1), match.getCurrentGuessCtr());
    }
}
