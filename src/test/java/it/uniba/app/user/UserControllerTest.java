package it.uniba.app.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests UserController class. */
public final class UserControllerTest {
    /** Class to test for player. */
    private UserController playerController;

    /** Class to test for wordsmith. */
    private UserController wordsmithController;

    /** Setups the test. */
    @BeforeEach
    public void setUp() {
        playerController = new UserController("Player");
        wordsmithController = new UserController("Wordsmith");
    }

    /** Tests the instantiation with an illegal player type. */
    @Test
    public void testUserController_invalidPlayerType() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> new UserController("illegal"));
    }

    /** Test isWordsmith method for player. */
    @Test
    public void testIsWordsmith_Player() {
        assertFalse(playerController.isWordsmith());
    }

    /** Test isWordsmith method for wordsmith. */
    @Test
    public void testIsWordsmith_Wordsmith() {
        assertTrue(wordsmithController.isWordsmith());
    }

    /** Test getHelpCommands for player. */
    @Test
    public void testGetHelpCommands_Player_NotEmpty() {
        assertTrue(playerController.getHelpCommands().length > 0);
    }

    /** Test getHelpCommands for wordsmith. */
    @Test
    public void testGetHelpCommands_Wordsmith_NotEmpty() {
        assertTrue(wordsmithController.getHelpCommands().length > 0);
    }

}
