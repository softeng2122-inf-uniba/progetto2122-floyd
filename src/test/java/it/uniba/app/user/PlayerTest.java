package it.uniba.app.user;

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
    public void testGetHelpCommands() {
        assertNotNull(player.getHelpCommands());
        assertTrue(player.getHelpCommands().length > 0);
    }

    @Test
    public void testIsWordsmith() {
        assertFalse(player.isWordsmith());
    }
}
