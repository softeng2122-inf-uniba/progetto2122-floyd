package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;

/** Test MatchInputDispatcher class. */
public final class MatchInputDispatcherTest {
    /** Standard InputStream. */
    private PrintStream stdOut;

    /** Test OutputStream. */
    private ByteArrayOutputStream outContent;

    /** Match object. */
    private Match match;

    /** UserInterface object. */
    private UserInterface ui;

    /**
     * Setups the test.
     *
     * @throws UnsupportedEncodingException
     */
    @BeforeEach
    public void setUp() throws UnsupportedEncodingException {
        ui = new UserInterface(new UserController("Wordsmith"));
        match = new Match(ui);
        match.setInProgress(true);
        match.setSecretWord("prova");

        stdOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
    }

    /**
     * Test execute with a correct guess "prova".
     * Checks if match ended since it was correct.
     */
    @Test
    public void testExecute_GuessAttempt_Valid() {
        assertTrue(match.isInProgress());
        new MatchInputDispatcher(match, ui).execute("prova");
        assertFalse(match.isInProgress());
    }

    /**
     * Test execute with a too long guess "provva".
     * Checks if it counted it as a guess and the output given.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testExecute_GuessAttempt_NotValid_TooLong() throws UnsupportedEncodingException {
        int old = match.getCurrentGuessCtr();
        new MatchInputDispatcher(match, ui).execute("provva");
        assertEquals(old, match.getCurrentGuessCtr());

        String outExpected = "Tentativo eccessivo" + System.lineSeparator();
        assertEquals(outExpected, outContent.toString("UTF-8"));
    }

    /**
     * Test execute with a too short guess "prov".
     * Checks if it counted it as a guess and the output given.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testExecute_GuessAttempt_NotValid_TooShort() throws UnsupportedEncodingException {
        int old = match.getCurrentGuessCtr();
        new MatchInputDispatcher(match, ui).execute("prov");
        assertEquals(old, match.getCurrentGuessCtr());

        String outExpected = "Tentativo incompleto" + System.lineSeparator();
        assertEquals(outExpected, outContent.toString("UTF-8"));
    }

    /**
     * Test execute with an invalid guess "pr2ov".
     * Checks if it counted it as a guess and the output given.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testExecute_GuessAttempt_NotValid_NotAlphabet() throws UnsupportedEncodingException {
        int old = match.getCurrentGuessCtr();
        new MatchInputDispatcher(match, ui).execute("pr2ov");
        assertEquals(old, match.getCurrentGuessCtr());

        String outExpected = "Tentativo non valido" + System.lineSeparator();
        assertEquals(outExpected, outContent.toString("UTF-8"));
    }

    /**
     * Test execute with a command "/help".
     * Checks the output given.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testExecute_Command() throws UnsupportedEncodingException {
        new MatchInputDispatcher(match, ui).execute("/help");

        String expectedOutput = System.lineSeparator()
                + String.join(System.lineSeparator(),
                        new UserController("Wordsmith").getHelpCommands())
                + System.lineSeparator()
                + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    /** Restore the std I/O streams. */
    @AfterEach
    public void restoreStream() {
        System.setOut(stdOut);
    }
}
