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
import org.junit.jupiter.api.Test;

import it.uniba.app.utils.UserInput;

public final class ConfirmationRequesterTest {

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
        InputStream in = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        assertTrue(objToTest.execute());
    }

    @Test
    public void testExecute_NoConfirmation() {
        String userInput = "n";
        InputStream in = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        assertFalse(objToTest.execute());
    }

    @Test
    public void testExecute_InvalidConfirmation() throws UnsupportedEncodingException {
        String userInput = "yy";
        InputStream in = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        PrintStream stdOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
        String expectedOut = "Opzione non valida!" + System.lineSeparator();

        assertFalse(objToTest.execute());
        assertEquals(expectedOut, outContent.toString("UTF-8"));

        System.setOut(stdOut);
    }

    @AfterEach
    public void restoreStream() {
        System.setIn(stdIn);
        UserInput.refreshStream();
    }

}
