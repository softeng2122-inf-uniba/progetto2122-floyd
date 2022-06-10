package it.uniba.app.ui.control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.ExitAssertions;
import it.uniba.app.utils.UserInput;

/** Test ExitRequestProcessor class. */
public final class ExitRequestProcessorTest {
    /** Class to test. */
    private ExitRequestProcessor objToTest;

    /** Standard InputStream. */
    private InputStream stdIn;

    /** Standard PrintStream. */
    private PrintStream stdOut;

    /** Test OutputStream. */
    private ByteArrayOutputStream outContent;

    /**
     * Setups the test.
     *
     * @throws UnsupportedEncodingException
     */
    @BeforeEach
    public void setUp() throws UnsupportedEncodingException {
        objToTest = new ExitRequestProcessor();
        stdOut = System.out;
        stdIn = System.in;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
    }

    /** Test execute method with a y in input. */
    @Test
    public void testExecute_DoesExit() {
        String userInput = "y";
        InputStream in = new ByteArrayInputStream(
                userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        ExitAssertions.assertExits(0,
                () -> objToTest.execute());
    }

    /**
     * Test execute method with a n in input.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testExecute_DoesNotExit() throws UnsupportedEncodingException {
        String userInput = "n";
        InputStream in = new ByteArrayInputStream(
                userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        objToTest.execute();

        String outExpected = "Sicuro di voler uscire dal gioco? Y/N: ";
        assertEquals(outExpected, outContent.toString("UTF-8"));
    }

    /** Restores the std I/O streams. */
    @AfterEach
    public void restoreStream() {
        System.setIn(stdIn);
        UserInput.refreshStream();
        System.setOut(stdOut);
    }

}
