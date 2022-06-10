package it.uniba.app.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import it.uniba.app.ExitAssertions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.uniba.app.user.UserController;
import it.uniba.app.utils.UserInput;

public final class UserInterfaceTest {
    /** UserInterface for wordsmith. */
    private UserInterface uiWordsmith;

    /** UserInterface for player. */
    private UserInterface uiPlayer;

    /** Test OutputStream. */
    private ByteArrayOutputStream outContent;

    /** Standard PrintStream. */
    private PrintStream stdOut;

    /** Standard InputStream. */
    private InputStream stdIn;

    /**
     * Setups the test.
     *
     * @throws UnsupportedEncodingException
     */
    @BeforeEach
    public void setUp() throws UnsupportedEncodingException {
        uiWordsmith = new UserInterface(new UserController("Wordsmith"));
        uiPlayer = new UserInterface(new UserController("Player"));
        outContent = new ByteArrayOutputStream();
        stdOut = System.out;
        stdIn = System.in;
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
    }

    /**
     * Tests getCommands as wordsmith with /help in input.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsWordsmith_Help() throws UnsupportedEncodingException {
        uiWordsmith.getCommands("/help");

        String expectedOutput = System.lineSeparator()
                + String.join(System.lineSeparator(),
                        new UserController("Wordsmith").getHelpCommands())
                + System.lineSeparator()
                + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    /**
     * Tests getCommands as player with /help in input.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsPlayer_Help() throws UnsupportedEncodingException {
        uiPlayer.getCommands("/help");

        String expectedOutput = System.lineSeparator()
                + String.join(System.lineSeparator(),
                        new UserController("Player").getHelpCommands())
                + System.lineSeparator()
                + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    /**
     * Tests getCommands as wordsmith with /gioca in input
     * when there's no secret word set.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsWordsmith_Play_NoSecretWord() throws UnsupportedEncodingException {
        uiWordsmith.getCommands("/gioca");

        String expectedOutput = "Parola segreta mancante"
                + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    /**
     * Tests getCommands as both with /esci in input.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsBoth_Exit() {
        String userInput = "y";
        InputStream in = new ByteArrayInputStream(
                userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        ExitAssertions.assertExits(0,
                () -> uiWordsmith.getCommands("/esci"));

        in = new ByteArrayInputStream(
                userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        ExitAssertions.assertExits(0,
                () -> uiPlayer.getCommands("/esci"));

        System.setIn(stdIn);
        UserInput.refreshStream();
    }

    /**
     * Tests getCommands as wordsmith with /mostra in input
     * when there's no secret word set.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsWordsmith_ShowSecretWord_Missing() throws UnsupportedEncodingException {
        uiWordsmith.getCommands("/mostra");
        String expectedOutput = "Parola segreta mancante"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    /**
     * Tests getCommands as wordsmith with /mostra in input
     * when there's a secret word set.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsWordsmith_ShowSecretWord() throws UnsupportedEncodingException {
        uiWordsmith.setLastSecretWord("prova");
        uiWordsmith.getCommands("/mostra");
        String expectedOutput = "La parola segreta è: prova"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    /**
     * Tests getCommands as wordsmith with /nuova prova in input.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsWordsmith_NewSecretWord() {
        uiWordsmith.getCommands("/nuova prova");
        assertEquals("prova", uiWordsmith.getLastSecretWord());
    }

    /**
     * Tests getCommands as wordsmith with /invalid in input.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsWordsmith_InvalidCommand_WithSlash() throws UnsupportedEncodingException {
        uiWordsmith.getCommands("/invalid");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    /**
     * Tests getCommands as wordsmith with invalid in input.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsWordsmith_InvalidCommand_WithoutSlash() throws UnsupportedEncodingException {
        uiWordsmith.getCommands("invalid");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    /**
     * Tests getCommands as player with /invalid in input.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsPlayer_InvalidCommand_WithSlash() throws UnsupportedEncodingException {
        uiPlayer.getCommands("/invalid");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    /**
     * Tests getCommands as player with invalid in input.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsPlayer_InvalidCommand_WithoutSlash() throws UnsupportedEncodingException {
        uiPlayer.getCommands("invalid");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    /**
     * Tests getCommands as player with /gioca in input
     * when there's no secret word set.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsPlayer_Play_NoSecretWord() throws UnsupportedEncodingException {
        uiPlayer.getCommands("/gioca");
        String expectedOutput = "Parola segreta mancante"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    /**
     * Tests getCommands as player with /mostra in input.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsPlayer_ShowSecretWord() throws UnsupportedEncodingException {
        uiPlayer.getCommands("/mostra");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    /** Test getCommands as player with /nuova prova in input. */
    @Nested
    class AsPlayer_NewSecretWord {
        /** Setups the test. */
        @BeforeEach
        public void setUp() {
            uiPlayer.getCommands("/nuova prova");
        }

        /**
         * Tests the output to check if meets the expected output.
         *
         * @throws UnsupportedEncodingException
         */
        @Test
        public void testOutput() throws UnsupportedEncodingException {
            String expectedOutput = "Comando non riconosciuto. "
                    + "/help per visualizzare la lista dei comandi."
                    + System.lineSeparator();
            assertEquals(expectedOutput, outContent.toString("UTF-8"));
        }

        /** Tests that lastSecretWord has not changed. */
        @Test
        public void testLastSecretWord_HasNotChanged() {
            assertNull(uiPlayer.getLastSecretWord());
        }
    }

    /**
     * Tests getCommands as Wordsmith with /abbandona in input
     * when there is a match in progress.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsWordsmith_Leave_MatchInProgress() throws UnsupportedEncodingException {
        String userInput = "/abbandona" + System.lineSeparator()
                + "y";
        InputStream in = new ByteArrayInputStream(
                userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        uiWordsmith.getCommands("/nuova prova");
        uiWordsmith.getCommands("/gioca");

        String[] content = outContent
                .toString("UTF-8")
                .split(System.lineSeparator());
        String expectedOut = "Hai abbandonato";
        assertTrue(content[content.length - 1].contains(expectedOut));

        System.setIn(stdIn);
        UserInput.refreshStream();
    }

    /**
     * Tests getCommands as Wordsmith with /abbandona in input
     * when there is not a match in progress.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsWordsmith_Leave_NoMatchToLeave() throws UnsupportedEncodingException {
        uiWordsmith.getCommands("/abbandona");

        String expectedOut = "Non è in corso alcuna partita da abbandonare.";
        assertTrue(outContent.toString("UTF-8").contains(expectedOut));
    }

    /**
     * Tests getCommands as player with /abbandona in input
     * when there is not a match in progress.
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testGetCommands_AsPlayer_Leave_NoMatchToLeave() throws UnsupportedEncodingException {
        uiPlayer.getCommands("/abbandona");

        String expectedOut = "Non è in corso alcuna partita da abbandonare.";
        assertTrue(outContent.toString("UTF-8").contains(expectedOut));
    }

    /** Restores the std I/O streams. */
    @AfterEach
    public void restoreStream() {
        System.setOut(stdOut);
    }

}
