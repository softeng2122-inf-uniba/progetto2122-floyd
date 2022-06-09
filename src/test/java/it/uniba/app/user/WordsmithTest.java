package it.uniba.app.user;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordsmithTest {
    private Wordsmith wordsmith;

    @BeforeEach
    public void setUp() {
        wordsmith = new Wordsmith();
    }

    @Test
    public void testGetHelpCommands_NotNull() {
        assertNotNull(wordsmith.getHelpCommands());
    }

    @Test
    public void testGetHelpCommands_NotEmpty() {
        assertTrue(wordsmith.getHelpCommands().length > 0);
    }

    @Test
    public void testGetHelpCommands_ContainsTheCorrectCommands() {
        final String[] COMMANDS = {
                "/help",
                "/gioca",
                "/abbandona",
                "/esci",
                "/nuova <parola>",
                "/mostra"
        };
        assertArrayEquals(COMMANDS, wordsmith.getHelpCommands());
    }

    @Test
    public void testIsWordsmith() {
        assertTrue(wordsmith.isWordsmith());
    }
}
