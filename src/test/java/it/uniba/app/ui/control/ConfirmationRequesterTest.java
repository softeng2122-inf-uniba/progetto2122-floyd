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
import org.junit.jupiter.api.Test;

public class ConfirmationRequesterTest {

    private ConfirmationRequester objToTest;
    private InputStream stdIn;

    @BeforeEach
    public void setUp() {
        stdIn = System.in;
        objToTest = new ConfirmationRequester();
    }

    @Test
    public void testExecute_YesConfirmation() {
        String userInput = "y";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        assertTrue(objToTest.execute());
    }

    @Test
    public void testExecute_NoConfirmation() {
        String userInput = "n";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        assertFalse(objToTest.execute());
    }

    @Test
    public void testExecute_InvalidConfirmation() {
        String userInput = "yy";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        PrintStream stdOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expectedOut = "Opzione non valida!" + System.lineSeparator();

        assertFalse(objToTest.execute());

        assertEquals(expectedOut, outContent.toString());

        System.setOut(stdOut);
    }

    @AfterEach
    public void restoreStream() {
        System.setIn(stdIn);
    }

}
