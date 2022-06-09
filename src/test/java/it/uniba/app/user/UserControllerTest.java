package it.uniba.app.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class UserControllerTest {
    private UserController playerController;
    private UserController wordsmithController;

    @BeforeEach
    public void setUp() {
        playerController = new UserController("Player");
        wordsmithController = new UserController("Wordsmith");
    }

    @Test
    public void testUserController_invalidPlayerType() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> new UserController("illegal"));
    }

    @Test
    public void testIsWordsmith_Player() {
        assertFalse(playerController.isWordsmith());
    }

    @Test
    public void testIsWordsmith_Wordsmith() {
        assertTrue(wordsmithController.isWordsmith());
    }

    @Test
    public void testGetHelpCommands_Player_NotEmpty() {
        assertTrue(playerController.getHelpCommands().length > 0);
    }

    @Test
    public void testGetHelpCommands_Wordsmith_NotEmpty() {
        assertTrue(wordsmithController.getHelpCommands().length > 0);
    }

}
