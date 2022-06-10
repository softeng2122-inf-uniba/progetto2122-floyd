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

/** Test MatchController class. */
public final class MatchControllerTest {
    /** UserInterface object. */
    private UserInterface ui;

    /** Class to test. */
    private MatchController matchController;

    /** Setups the test. */
    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        matchController = new MatchController(ui);
    }

    /** Test startMatch method. */
    @Nested
    class TestStartMatch {
        /** Standard InputStream. */
        private InputStream stdIn;

        /** Setups the test. */
        @BeforeEach
        public void setUp() {
            stdIn = System.in;
        }

        /**
         * Test to end the match with a correct guess.
         * Checks if the match has been resetted, since it ended.
         */
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

        /**
         * Test to end the match without guessing.
         * Checks if the match has been resetted, since it ended.
         */
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

        /** Restore the std I/O streams. */
        @AfterEach
        public void restoreStream() {
            System.setIn(stdIn);
            UserInput.refreshStream();
        }
    }

    /**
     * Test endMatch method.
     * Checks if isInProgress has been set to false.
     */
    @Test
    public void testEndMatch() {
        matchController.setInProgress(true);
        matchController.endMatch();
        assertFalse(matchController.isInProgress());
    }
}
