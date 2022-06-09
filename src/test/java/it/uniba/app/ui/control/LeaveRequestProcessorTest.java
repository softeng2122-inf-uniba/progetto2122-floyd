package it.uniba.app.ui.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;
import it.uniba.app.utils.UserInput;

public class LeaveRequestProcessorTest {

    private InputStream stdIn;
    private PrintStream stdOut;
    private ByteArrayOutputStream outContent;

    private UserInterface ui;
    private MatchController matchController;
    private LeaveRequestProcessor objToTest;

    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        matchController = new MatchController(ui);
        objToTest = new LeaveRequestProcessor(matchController);
        stdOut = System.out;
        stdIn = System.in;
    }

    @Nested
    class MatchInProgress {
        @BeforeEach
        public void setUp() {
            matchController.setInProgress(true);
        }

        @Test
        public void testExecute_MatchInProgress_Leave() {
            String userInput = "y";
            InputStream in = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(in);
            UserInput.refreshStream();

            objToTest.execute();

            assertFalse(matchController.isInProgress());
        }

        @Test
        public void testExecute_MatchInProgress_DontLeave() {
            String userInput = "n";
            InputStream in = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(in);
            UserInput.refreshStream();

            objToTest.execute();

            assertTrue(matchController.isInProgress());
        }

        @AfterEach
        public void restoreStream() {
            System.setIn(stdIn);
            UserInput.refreshStream();
        }
    }

    @Test
    public void testExecute_MatchNotInProgress() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String outExpected = "Non è in corso alcuna partita da abbandonare." + System.lineSeparator();
        objToTest.execute();
        assertEquals(outExpected, outContent.toString());
        System.setOut(stdOut);
    }

}
