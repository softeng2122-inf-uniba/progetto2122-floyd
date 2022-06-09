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
    private UserInterface uiWordsmith;
    private UserInterface uiPlayer;
    private ByteArrayOutputStream outContent;
    private PrintStream stdOut;
    private InputStream stdIn;

    @BeforeEach
    public void setUp() throws UnsupportedEncodingException {
        uiWordsmith = new UserInterface(new UserController("Wordsmith"));
        uiPlayer = new UserInterface(new UserController("Player"));
        outContent = new ByteArrayOutputStream();
        stdOut = System.out;
        stdIn = System.in;
        System.setOut(new PrintStream(outContent, false, "UTF-8"));
    }

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

    @Test
    public void testGetCommands_AsWordsmith_Play_NoSecretWord() throws UnsupportedEncodingException {
        uiWordsmith.getCommands("/gioca");

        String expectedOutput = "Parola segreta mancante"
                + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

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

    @Test
    public void testGetCommands_AsWordsmith_ShowSecretWord_Missing() throws UnsupportedEncodingException {
        uiWordsmith.getCommands("/mostra");
        String expectedOutput = "Parola segreta mancante"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    @Test
    public void testGetCommands_AsWordsmith_ShowSecretWord() throws UnsupportedEncodingException {
        uiWordsmith.setLastSecretWord("prova");
        uiWordsmith.getCommands("/mostra");
        String expectedOutput = "La parola segreta è: prova"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    @Test
    public void testGetCommands_AsWordsmith_NewSecretWord() {
        uiWordsmith.getCommands("/nuova prova");
        assertEquals("prova", uiWordsmith.getLastSecretWord());
    }

    @Test
    public void testGetCommands_AsWordsmith_InvalidCommand_WithSlash() throws UnsupportedEncodingException {
        uiWordsmith.getCommands("/invalid");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    @Test
    public void testGetCommands_AsWordsmith_InvalidCommand_WithoutSlash() throws UnsupportedEncodingException {
        uiWordsmith.getCommands("invalid");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    @Test
    public void testGetCommands_AsPlayer_InvalidCommand_WithSlash() throws UnsupportedEncodingException {
        uiPlayer.getCommands("/invalid");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    @Test
    public void testGetCommands_AsPlayer_InvalidCommand_WithoutSlash() throws UnsupportedEncodingException {
        uiPlayer.getCommands("invalid");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    @Test
    public void testGetCommands_AsPlayer_Play_NoSecretWord() throws UnsupportedEncodingException {
        uiPlayer.getCommands("/gioca");
        String expectedOutput = "Parola segreta mancante"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    @Test
    public void testGetCommands_AsPlayer_ShowSecretWord() throws UnsupportedEncodingException {
        uiPlayer.getCommands("/mostra");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString("UTF-8"));
    }

    @Nested
    class AsPlayer_NewSecretWord {
        @BeforeEach
        public void setUp() {
            uiPlayer.getCommands("/nuova prova");
        }

        @Test
        public void testOutput() throws UnsupportedEncodingException {
            String expectedOutput = "Comando non riconosciuto. "
                    + "/help per visualizzare la lista dei comandi."
                    + System.lineSeparator();
            assertEquals(expectedOutput, outContent.toString("UTF-8"));
        }

        @Test
        public void testLastSecretWord_HasNotChanged() {
            assertNull(uiPlayer.getLastSecretWord());
        }
    }

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

    @Test
    public void testGetCommands_AsWordsmith_Leave_NoMatchToLeave() throws UnsupportedEncodingException {
        uiWordsmith.getCommands("/abbandona");

        String expectedOut = "Non è in corso alcuna partita da abbandonare.";
        assertTrue(outContent.toString("UTF-8").contains(expectedOut));
    }

    @Test
    public void testGetCommands_AsPlayer_Leave_NoMatchToLeave() throws UnsupportedEncodingException {
        uiPlayer.getCommands("/abbandona");

        String expectedOut = "Non è in corso alcuna partita da abbandonare.";
        assertTrue(outContent.toString("UTF-8").contains(expectedOut));
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(stdOut);
    }

}
