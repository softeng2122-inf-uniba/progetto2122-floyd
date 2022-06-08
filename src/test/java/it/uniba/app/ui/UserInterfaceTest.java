package it.uniba.app.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import it.uniba.app.ExitAssertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.user.UserController;
import it.uniba.app.utils.UserInput;

public class UserInterfaceTest {
    private UserInterface uiWordsmith;
    private UserInterface uiPlayer;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        uiWordsmith = new UserInterface(new UserController("Wordsmith"));
        uiPlayer = new UserInterface(new UserController("Player"));
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testGetCommands_AsWordsmith_Help() {
        uiWordsmith.getCommands("/help");

        String expectedOutput = System.lineSeparator();
        expectedOutput = expectedOutput
                + String.join(System.lineSeparator(), new UserController("Wordsmith").getHelpCommands());
        expectedOutput = expectedOutput + System.lineSeparator() + System.lineSeparator() + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetCommands_AsPlayer_Help() {
        uiPlayer.getCommands("/help");

        String expectedOutput = System.lineSeparator();
        expectedOutput = expectedOutput
                + String.join(System.lineSeparator(), new UserController("Player").getHelpCommands());
        expectedOutput = expectedOutput + System.lineSeparator() + System.lineSeparator() + System.lineSeparator();

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
        final InputStream stdIn = System.in;
        String userInput = "/esci" + System.lineSeparator() + "y"
                + System.lineSeparator() + "/esci"
                + System.lineSeparator() + "y";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        ExitAssertions.assertExits(0, () -> uiWordsmith.getCommands(UserInput.get()));
        ExitAssertions.assertExits(0, () -> uiPlayer.getCommands(UserInput.get()));

        System.setIn(stdIn);
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
        uiWordsmith.getCommands("/mostra");

        String expectedOutput = "OK" + System.lineSeparator()
                + "La parola segreta è: prova" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
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

    @Test
    public void testGetCommands_AsPlayer_NewSecretWord() {
        uiPlayer.getCommands("/nuova prova");
        String expectedOutput = "Comando non riconosciuto. "
                + "/help per visualizzare la lista dei comandi."
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

}
