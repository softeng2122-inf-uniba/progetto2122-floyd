package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import it.uniba.app.user.UserController;

/**
 * Main test class of the application.
 */
public class AppTest {
    @Test
    public void testMain() {
        String[] args = { "--help", "-h" };

        InputStream stdIn = System.in;
        InputStream in = new ByteArrayInputStream("userInput".getBytes());
        System.setIn(in);

        PrintStream stdOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertThrowsExactly(NoSuchElementException.class, () -> App.main(args));

        String outExpected = " __      __                .___.__          " + System.lineSeparator()
                + "/  \\    /  \\___________  __| _/|  |   ____  " + System.lineSeparator()
                + "\\   \\/\\/   /  _ \\_  __ \\/ __ | |  | _/ __ \\ " + System.lineSeparator()
                + " \\        (  <_> )  | \\/ /_/ | |  |_\\  ___/ " + System.lineSeparator()
                + "  \\__/\\  / \\____/|__|  \\____ | |____/\\___  >" + System.lineSeparator()
                + "       \\/                   \\/           \\/ |" + System.lineSeparator()
                + System.lineSeparator()
                + "--------------------------------------------" + System.lineSeparator()
                + System.lineSeparator()
                + "Benvenuto su Wordle!" + System.lineSeparator()
                + "Digitare /help per la lista dei comandi." + System.lineSeparator()
                + System.lineSeparator()
                + System.lineSeparator()
                + String.join(System.lineSeparator(), new UserController("Wordsmith").getHelpCommands())
                + System.lineSeparator()
                + System.lineSeparator()
                + System.lineSeparator()
                + "Comando non riconosciuto. /help per visualizzare la lista dei comandi."
                + System.lineSeparator();

        assertEquals(outExpected, outContent.toString());

        System.setIn(stdIn);
        System.setOut(stdOut);
    }
}
