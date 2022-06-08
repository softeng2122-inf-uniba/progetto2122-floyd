package it.uniba.app.ui.control;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

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
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        matchController = new MatchController(ui);
        objToTest = new PlayRequestProcessor(matchController);
        stdOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testExecute_MatchAlreadyInProgress() {
        matchController.setInProgress(true);
        String outExpected = "La partita è già in corso!" + System.lineSeparator();
        objToTest.execute("prova");
        assertEquals(outExpected, outContent.toString());
    }

    @Test
    public void testExecute_SecretWordMissing() {
        objToTest.execute(null);
        String outExpected = "Parola segreta mancante" + System.lineSeparator();
        assertEquals(outExpected, outContent.toString());
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(stdOut);
    }
}
