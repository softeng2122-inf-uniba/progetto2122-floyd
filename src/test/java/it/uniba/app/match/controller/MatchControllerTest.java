package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;

public class MatchControllerTest {
    private UserInterface ui;
    private MatchController matchController;

    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        matchController = new MatchController(ui);

    }

    @Test
    public void testStart() {
        InputStream stdIn = System.in;

        String userInput = "prova";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        matchController.start("prova");
        assertEquals(0, matchController.getCurrentGuessNumber());
        assertEquals(false, matchController.isCurrentGuessCorrect());
        assertEquals("prova", matchController.getSecretWord());

        System.setIn(stdIn);
    }

    @Test
    public void testEndMatch() {
        matchController.setInProgress(true);
        matchController.endMatch();
        assertFalse(matchController.isInProgress());
    }
}
