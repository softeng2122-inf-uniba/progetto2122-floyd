package it.uniba.app.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InputCheckerTest {
    @Test
    public void testIsCommand_True() {
        assertTrue(InputChecker.isCommand("/command"));
    }

    @Test
    public void testIsCommand_False() {
        assertFalse(InputChecker.isCommand("command"));
    }

    @Test
    public void testIsCommand_False_BackslashCommand() {
        assertFalse(InputChecker.isCommand("\\command"));
    }
}
