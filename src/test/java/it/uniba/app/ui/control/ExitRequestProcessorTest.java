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

public final class ExitRequestProcessorTest {
    private ExitRequestProcessor objToTest;
    private InputStream stdIn;
    private PrintStream stdOut;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() throws UnsupportedEncodingException {
        objToTest = new ExitRequestProcessor();
        stdOut = System.out;
        stdIn = System.in;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
    }

    @Test
    public void testExecute_DoesExit() {
        String userInput = "y";
        InputStream in = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        ExitAssertions.assertExits(0, () -> objToTest.execute());
    }

    @Test
    public void testExecute_DoesNotExit() throws UnsupportedEncodingException {
        String userInput = "n";
        InputStream in = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        objToTest.execute();

        String outExpected = "Sicuro di voler uscire dal gioco? Y/N: ";
        assertEquals(outExpected, outContent.toString("UTF-8"));
    }

    @AfterEach
    public void restoreStream() {
        System.setIn(stdIn);
        UserInput.refreshStream();
        System.setOut(stdOut);
    }

}
