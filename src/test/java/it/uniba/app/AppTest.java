package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.user.UserController;

/**
 * Main test class of the application.
 */
public class AppTest {
    InputStream stdIn;
    InputStream in;
    PrintStream stdOut;
    ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        stdIn = System.in;
        in = new ByteArrayInputStream("".getBytes());
        System.setIn(in);

        stdOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testMain_Help_Arg() {
        String[] args = { "--help" };

        // Chiederà input ma noi vogliamo solo verificare l'output
        assertThrowsExactly(NoSuchElementException.class, () -> App.main(args));

        String outExpected = System.lineSeparator()
                + String.join(System.lineSeparator(), new UserController("Wordsmith").getHelpCommands())
                + System.lineSeparator()
                + System.lineSeparator()
                + System.lineSeparator();

        assertTrue(outContent.toString().contains(outExpected));
    }

    @Test
    public void testMain_H_Arg() {
        String[] args = { "-h" };

        // Chiederà input ma noi vogliamo solo verificare l'output
        assertThrowsExactly(NoSuchElementException.class, () -> App.main(args));

        String outExpected = System.lineSeparator()
                + String.join(System.lineSeparator(), new UserController("Wordsmith").getHelpCommands())
                + System.lineSeparator()
                + System.lineSeparator()
                + System.lineSeparator();

        assertTrue(outContent.toString().contains(outExpected));
    }

    @AfterEach
    public void restoreStream() {
        System.setIn(stdIn);
        System.setOut(stdOut);
    }
}
