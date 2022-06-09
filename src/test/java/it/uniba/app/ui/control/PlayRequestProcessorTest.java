package it.uniba.app.ui.control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;

public class PlayRequestProcessorTest {
    private PrintStream stdOut;
    private ByteArrayOutputStream outContent;

    private UserInterface ui;
    private MatchController matchController;
    private PlayRequestProcessor objToTest;

    @BeforeEach
    public void setUp() throws UnsupportedEncodingException {
        ui = new UserInterface(new UserController("Wordsmith"));
        matchController = new MatchController(ui);
        objToTest = new PlayRequestProcessor(matchController);
        stdOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
    }

    @Test
    public void testExecute_MatchAlreadyInProgress() throws UnsupportedEncodingException {
        matchController.setInProgress(true);
        String outExpected = "La partita è già in corso!" + System.lineSeparator();
        objToTest.execute("prova");
        assertEquals(outExpected, outContent.toString("UTF-8"));
    }

    @Test
    public void testExecute_SecretWordMissing() throws UnsupportedEncodingException {
        objToTest.execute(null);
        String outExpected = "Parola segreta mancante" + System.lineSeparator();
        assertEquals(outExpected, outContent.toString("UTF-8"));
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(stdOut);
    }
}
