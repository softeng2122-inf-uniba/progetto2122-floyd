package it.uniba.app.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Tests InputChecker class. */
public final class InputCheckerTest {

    /** Tests isCommand method with a real command. */
    @Test
    public void testIsCommand_True() {
        assertTrue(InputChecker.isCommand("/command"));
    }

    /** Tests isCommand method without a command. */
    @Test
    public void testIsCommand_False() {
        assertFalse(InputChecker.isCommand("command"));
    }

    /** Tests isCommand method with an invalid command. */
    @Test
    public void testIsCommand_False_BackslashCommand() {
        assertFalse(InputChecker.isCommand("\\command"));
    }

    /** Tests isValidAsWord method with a correct word. */
    @Test
    public void testIsValidAsWord_CorrectLenght_Alphabet() {
        assertTrue(InputChecker.isValidAsWord("abcde"));
    }

    /**
     * Tests isValidAsWord method with an incorrect word
     * because too short.
     */
    @Test
    public void testIsValidAsWord_TooShort() {
        assertFalse(InputChecker.isValidAsWord("abcd"));
    }

    /**
     * Tests isValidAsWord method with an incorrect word
     * because too short and invalid.
     */
    @Test
    public void testIsValidAsWord_TooShort_AnyCharacter() {
        assertFalse(InputChecker.isValidAsWord("a!cd"));
    }

    /**
     * Tests isValidAsWord method with an incorrect word
     * because too long.
     */
    @Test
    public void testIsValidAsWord_TooLong() {
        assertFalse(InputChecker.isValidAsWord("abcdef"));
    }

    /**
     * Tests isValidAsWord method with an incorrect word
     * because too long and invalid.
     */
    @Test
    public void testIsValidAsWord_TooLong_AnyCharacter() {
        assertFalse(InputChecker.isValidAsWord("ab^def"));
    }

    /**
     * Tests isValidAsWord method with an incorrect word
     * because invalid.
     */
    @Test
    public void testIsValidAsWord_CorrectLenght_AnyNonAlphabet() {
        assertFalse(InputChecker.isValidAsWord("ab@de"));
    }

    /**
     * Tests isValidAsWord method with an incorrect word
     * because invalid.
     */
    @Test
    public void testIsValidAsWord_CorrectLenght_Alphabet_UpperCase() {
        assertFalse(InputChecker.isValidAsWord("aBcde"));
    }
}

