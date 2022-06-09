package it.uniba.app.ui.control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.ExitAssertions;
import it.uniba.app.utils.UserInput;

public class ExitRequestProcessorTest {
    private ExitRequestProcessor objToTest;
    private InputStream stdIn;
    private PrintStream stdOut;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        objToTest = new ExitRequestProcessor();
        stdOut = System.out;
        stdIn = System.in;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testExecute_DoesExit() {
        String userInput = "y";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        UserInput.refreshStream();

        ExitAssertions.assertExits(0, () -> objToTest.execute());
    }

    @Test
    public void testExecute_DoesNotExit() {
        String userInput = "n";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        UserInput.refreshStream();

        objToTest.execute();

        String outExpected = "Sicuro di voler uscire dal gioco? Y/N: ";
        assertEquals(outExpected, outContent.toString());
    }

    @AfterEach
    public void restoreStream() {
        System.setIn(stdIn);
        UserInput.refreshStream();
        System.setOut(stdOut);
    }

}
