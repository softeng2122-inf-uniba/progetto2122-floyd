package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;

public class MatchStatusUpdaterTest {
    private Match match;
    private UserInterface ui;

    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        match = new Match(ui);
        match.setInProgress(true);
    }

    @Test
    public void testExecute_CorrectGuess() {
        new MatchStatusUpdater(match).execute("true");
        assertFalse(match.isInProgress());
    }

    @Test
    public void testExecute_IncorrectGuess() {
        int oldCtr = match.getCurrentGuessCtr();
        new MatchStatusUpdater(match).execute("false");
        assertTrue(match.isInProgress());
        assertEquals((oldCtr + 1), match.getCurrentGuessCtr());
    }
}
