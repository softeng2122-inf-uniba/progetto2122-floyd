package it.uniba.app.user;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testGetHelpCommands_NotNull() {
        assertNotNull(player.getHelpCommands());
    }

    @Test
    public void testGetHelpCommands_NotEmpty() {
        assertTrue(player.getHelpCommands().length > 0);
    }

    @Test
    public void testGetHelpCommands_ContainsTheCorrectCommands() {
        final String[] COMMANDS = {
                "/help",
                "/gioca",
                "/abbandona",
                "/esci"
        };
        assertArrayEquals(COMMANDS, player.getHelpCommands());
    }

    @Test
    public void testIsWordsmith() {
        assertFalse(player.isWordsmith());
    }
}
