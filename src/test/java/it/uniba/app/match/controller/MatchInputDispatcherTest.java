package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;

public class MatchInputDispatcherTest {
    private PrintStream stdOut;
    private ByteArrayOutputStream outContent;

    private Match match;
    private UserInterface ui;

    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        match = new Match(ui);
        match.setInProgress(true);
        match.setSecretWord("prova");

        stdOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testExecute_GuessAttempt_Valid() {
        assertTrue(match.isInProgress());
        new MatchInputDispatcher(match, ui).execute("prova");
        assertFalse(match.isInProgress());
    }

    @Test
    public void testExecute_GuessAttempt_NotValid_TooLong() {
        assertTrue(match.isInProgress());
        new MatchInputDispatcher(match, ui).execute("provva");
        assertTrue(match.isInProgress());

        String outExpected = "Tentativo eccessivo" + System.lineSeparator();
        assertEquals(outExpected, outContent.toString());
    }

    @Test
    public void testExecute_GuessAttempt_NotValid_TooShort() {
        assertTrue(match.isInProgress());
        new MatchInputDispatcher(match, ui).execute("prov");
        assertTrue(match.isInProgress());

        String outExpected = "Tentativo incompleto" + System.lineSeparator();
        assertEquals(outExpected, outContent.toString());
    }

    @Test
    public void testExecute_GuessAttempt_NotValid_NotAlphabet() {
        assertTrue(match.isInProgress());
        new MatchInputDispatcher(match, ui).execute("pr2ov");
        assertTrue(match.isInProgress());

        String outExpected = "Tentativo non valido" + System.lineSeparator();
        assertEquals(outExpected, outContent.toString());
    }

    @Test
    public void testExecute_Command_Help() {
        new MatchInputDispatcher(match, ui).execute("/help");

        String expectedOutput = System.lineSeparator();
        expectedOutput = expectedOutput
                + String.join(System.lineSeparator(), new UserController("Wordsmith").getHelpCommands());
        expectedOutput = expectedOutput + System.lineSeparator() + System.lineSeparator() + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(stdOut);
    }
}
