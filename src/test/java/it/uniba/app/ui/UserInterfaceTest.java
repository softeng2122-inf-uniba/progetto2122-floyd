package it.uniba.app.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import it.uniba.app.ExitAssertions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import it.uniba.app.user.UserController;
import it.uniba.app.utils.UserInput;

public class UserInterfaceTest {
    private UserInterface uiWordsmith;
    private UserInterface uiPlayer;
    private ByteArrayOutputStream outContent;
    private PrintStream stdOut;
    private InputStream stdIn;

    @BeforeEach
    public void setUp() {
        uiWordsmith = new UserInterface(new UserController("Wordsmith"));
        uiPlayer = new UserInterface(new UserController("Player"));
        outContent = new ByteArrayOutputStream();
        stdOut = System.out;
        stdIn = System.in;
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testGetCommands_AsWordsmith_Help() {
        uiWordsmith.getCommands("/help");

        String expectedOutput = System.lineSeparator()
                + String.join(System.lineSeparator(), new UserController("Wordsmith").getHelpCommands())
                + System.lineSeparator() + System.lineSeparator() + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetCommands_AsPlayer_Help() {
        uiPlayer.getCommands("/help");

        String expectedOutput = System.lineSeparator()
                + String.join(System.lineSeparator(), new UserController("Player").getHelpCommands())
                + System.lineSeparator() + System.lineSeparator() + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetCommands_AsWordsmith_Play_NoSecretWord() {
        uiWordsmith.getCommands("/gioca");

        String expectedOutput = "Parola segreta mancante" + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetCommands_AsBoth_Exit() {
        String userInput = "y";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        UserInput.refreshStream();

        ExitAssertions.assertExits(0, () -> uiWordsmith.getCommands("/esci"));

        in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        UserInput.refreshStream();

        ExitAssertions.assertExits(0, () -> uiPlayer.getCommands("/esci"));

        System.setIn(stdIn);
        UserInput.refreshStream();
    }

    @Test
    public void testGetCommands_AsWordsmith_ShowSecretWord_Missing() {
        uiWordsmith.getCommands("/mostra");
        String expectedOutput = "Parola segreta mancante" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetCommands_AsWordsmith_ShowSecretWord() {
        uiWordsmith.setLastSecretWord("prova");
        uiWordsmith.getCommands("/mostra");
        String expectedOutput = "La parola segreta è: prova" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetCommands_AsWordsmith_NewSecretWord() {
        uiWordsmith.getCommands("/nuova prova");
        assertEquals("prova", uiWordsmith.getLastSecretWord());
    }

    @Test
    public void testGetCommands_AsWordsmith_InvalidCommand_WithSlash() {
        uiWordsmith.getCommands("/invalid");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetCommands_AsWordsmith_InvalidCommand_WithoutSlash() {
        uiWordsmith.getCommands("invalid");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetCommands_AsPlayer_InvalidCommand_WithSlash() {
        uiPlayer.getCommands("/invalid");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetCommands_AsPlayer_InvalidCommand_WithoutSlash() {
        uiPlayer.getCommands("invalid");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetCommands_AsPlayer_Play_NoSecretWord() {
        uiPlayer.getCommands("/gioca");
        String expectedOutput = "Parola segreta mancante" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetCommands_AsPlayer_ShowSecretWord() {
        uiPlayer.getCommands("/mostra");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Nested
    class AsPlayer_NewSecretWord {
        @BeforeEach
        public void setUp() {
            uiPlayer.getCommands("/nuova prova");
        }

        @Test
        public void testOutput() {
            String expectedOutput = "Comando non riconosciuto. "
                    + "/help per visualizzare la lista dei comandi."
                    + System.lineSeparator();
            assertEquals(expectedOutput, outContent.toString());
        }

        @Test
        public void testLastSecretWord_HasNotChanged() {
            assertNull(uiPlayer.getLastSecretWord());
        }
    }

    @Test
    public void testGetCommands_AsWordsmith_Leave_MatchInProgress() {
        String userInput = "/abbandona" + System.lineSeparator()
                + "y";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        UserInput.refreshStream();

        uiWordsmith.getCommands("/nuova prova");
        uiWordsmith.getCommands("/gioca");

        String[] content = outContent.toString().split(System.lineSeparator());
        String expectedOut = "Hai abbandonato";
        assertTrue(content[content.length - 1].contains(expectedOut));

        System.setIn(stdIn);
        UserInput.refreshStream();
    }

    @Test
    public void testGetCommands_AsWordsmith_Leave_NoMatchToLeave() {
        uiWordsmith.getCommands("/abbandona");

        String expectedOut = "Non è in corso alcuna partita da abbandonare.";
        assertTrue(outContent.toString().contains(expectedOut));
    }

    @Test
    public void testGetCommands_AsPlayer_Leave_NoMatchToLeave() {
        uiPlayer.getCommands("/abbandona");

        String expectedOut = "Non è in corso alcuna partita da abbandonare.";
        assertTrue(outContent.toString().contains(expectedOut));
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(stdOut);
    }

}
