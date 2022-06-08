package it.uniba.app.user;

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
    public void testGetHelpCommands() {
        assertNotNull(wordsmith.getHelpCommands());
        assertTrue(wordsmith.getHelpCommands().length > 0);
    }

    @Test
    public void testIsWordsmith() {
        assertTrue(wordsmith.isWordsmith());
    }
}
