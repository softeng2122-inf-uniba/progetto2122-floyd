package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;

public final class GuessAttemptManagerTest {
    private Match match;
    private UserInterface ui;

    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        match = new Match(ui);
        match.setSecretWord("prova");
    }

    @Test
    public void testExecute_CorrectGuess() {
        assertTrue(new GuessAttemptManager(match).execute("prova"));
    }

    @Test
    public void testExecute_IncorrectGuess() {
        assertFalse(new GuessAttemptManager(match).execute("papap"));
    }
}
