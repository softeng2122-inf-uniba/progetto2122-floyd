package it.uniba.app.ui.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;
import it.uniba.app.utils.UserInput;

/** Test LeaveRequestProcessor class. */
public final class LeaveRequestProcessorTest {

    /** Standard InputStream. */
    private InputStream stdIn;

    /** Standard PrintStream. */
    private PrintStream stdOut;

    /** Test OutputStream. */
    private ByteArrayOutputStream outContent;

    /** UserInterface object. */
    private UserInterface ui;

    /** MatchController object. */
    private MatchController matchController;

    /** Class to test. */
    private LeaveRequestProcessor objToTest;

    /** Setups the test. */
    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        matchController = new MatchController(ui);
        objToTest = new LeaveRequestProcessor(matchController);
        stdOut = System.out;
        stdIn = System.in;
    }

    /** Test execute method while match is in progress. */
    @Nested
    class MatchInProgress {

        /** Setups the test. */
        @BeforeEach
        public void setUp() {
            matchController.setInProgress(true);
        }

        /** Input y. */
        @Test
        public void testExecute_MatchInProgress_Leave() {
            String userInput = "y";
            InputStream in = new ByteArrayInputStream(
                    userInput.getBytes(StandardCharsets.UTF_8));
            System.setIn(in);
            UserInput.refreshStream();

            objToTest.execute();

            assertFalse(matchController.isInProgress());
        }

        /** Input n. */
        @Test
        public void testExecute_MatchInProgress_DontLeave() {
            String userInput = "n";
            InputStream in = new ByteArrayInputStream(
                    userInput.getBytes(StandardCharsets.UTF_8));
            System.setIn(in);
            UserInput.refreshStream();

            objToTest.execute();

            assertTrue(matchController.isInProgress());
        }

        /** Restores the std I/O streams. */
        @AfterEach
        public void restoreStream() {
            System.setIn(stdIn);
            UserInput.refreshStream();
        }
    }

    /**
     * Test execute method while match is not in progress.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testExecute_MatchNotInProgress() throws UnsupportedEncodingException {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, false, "UTF-8"));

        String outExpected = "Non è in corso alcuna partita da abbandonare."
                + System.lineSeparator();
        objToTest.execute();
        assertEquals(outExpected, outContent.toString("UTF-8"));
        System.setOut(stdOut);
    }

}
