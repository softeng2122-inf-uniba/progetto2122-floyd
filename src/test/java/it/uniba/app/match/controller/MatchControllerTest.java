package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;
import it.uniba.app.utils.UserInput;

public final class MatchControllerTest {
    private UserInterface ui;
    private MatchController matchController;

    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        matchController = new MatchController(ui);
    }

    @Nested
    public class TestStartMatch {
        private InputStream stdIn;

        @BeforeEach
        public void setUp() {
            stdIn = System.in;
        }

        @Test
        public void testStartMatch_CorrectGuess() {
            String userInput = "prova";
            InputStream in = new ByteArrayInputStream(
                    userInput.getBytes(StandardCharsets.UTF_8));
            System.setIn(in);
            UserInput.refreshStream();

            matchController.startMatch("prova");
            assertEquals(0, matchController.getCurrentGuessNumber());
            assertEquals(false, matchController.isCurrentGuessCorrect());
            assertEquals("prova", matchController.getSecretWord());
        }

        @Test
        public void testStartMatch_NoGuess() {
            String userInput = "ciaoo" + System.lineSeparator()
                    + "ciaoo" + System.lineSeparator()
                    + "ciaoo" + System.lineSeparator()
                    + "ciaoo" + System.lineSeparator()
                    + "ciaoo" + System.lineSeparator()
                    + "ciaoo" + System.lineSeparator();
            InputStream in = new ByteArrayInputStream(
                    userInput.getBytes(StandardCharsets.UTF_8));
            System.setIn(in);
            UserInput.refreshStream();

            matchController.startMatch("prova");
            assertEquals(0, matchController.getCurrentGuessNumber());
            assertEquals(false, matchController.isCurrentGuessCorrect());
            assertEquals("prova", matchController.getSecretWord());
        }

        @AfterEach
        public void restoreStream() {
            System.setIn(stdIn);
            UserInput.refreshStream();
        }
    }

    @Test
    public void testEndMatch() {
        matchController.setInProgress(true);
        matchController.endMatch();
        assertFalse(matchController.isInProgress());
    }
}
