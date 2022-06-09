package it.uniba.app.ui.control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;

public class NewSecretWordProcessorTest {
    private PrintStream stdOut;
    private ByteArrayOutputStream outContent;

    NewSecretWordProcessor objToTest;
    UserInterface ui;
    MatchController matchController;

    @BeforeEach
    public void setUp() throws UnsupportedEncodingException {
        ui = new UserInterface(new UserController("Wordsmith"));
        matchController = new MatchController(ui);
        objToTest = new NewSecretWordProcessor(ui, matchController);
        stdOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
    }

    @Test
    public void textExecute_Valid_MatchInProgress() {
        matchController.setInProgress(true);
        objToTest.execute("prova");
        assertEquals("prova", matchController.getSecretWord());
        assertEquals("prova", ui.getLastSecretWord());
    }

    @Test
    public void testExecute_Valid_MatchNotInProgress() {
        objToTest.execute("prova");
        assertNull(matchController.getSecretWord());
        assertEquals("prova", ui.getLastSecretWord());
    }

    @Test
    public void testExecute_NotValid_TooShort() throws UnsupportedEncodingException {
        String outExpected = "Parola segreta troppo corta" + System.lineSeparator();
        objToTest.execute("prov");
        assertEquals(outExpected, outContent.toString("UTF-8"));
    }

    @Test
    public void testExecute_NotValid_TooLong() throws UnsupportedEncodingException {
        String outExpected = "Parola segreta troppo lunga" + System.lineSeparator();
        objToTest.execute("provaa");
        assertEquals(outExpected, outContent.toString("UTF-8"));
    }

    @Test
    public void testExecute_NotValid_NotAlphabet() throws UnsupportedEncodingException {
        String outExpected = "Parola segreta non valida!" + System.lineSeparator();
        objToTest.execute("pr0va");
        assertEquals(outExpected, outContent.toString("UTF-8"));
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(stdOut);
    }
}
