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

    @Test
    public void testIsValidAsWord_CorrectLenght_Alphabet() {
        assertTrue(InputChecker.isValidAsWord("abcde"));
    }

    @Test
    public void testIsValidAsWord_TooShort() {
        assertFalse(InputChecker.isValidAsWord("abcd"));
    }

    @Test
    public void testIsValidAsWord_TooShort_AnyCharacter() {
        assertFalse(InputChecker.isValidAsWord("a!cd"));
    }

    @Test
    public void testIsValidAsWord_TooLong() {
        assertFalse(InputChecker.isValidAsWord("abcdef"));
    }

    @Test
    public void testIsValidAsWord_TooLong_AnyCharacter() {
        assertFalse(InputChecker.isValidAsWord("ab^def"));
    }

    @Test
    public void testIsValidAsWord_CorrectLenght_AnyNonAlphabet() {
        assertFalse(InputChecker.isValidAsWord("ab@de"));
    }

    @Test
    public void testIsValidAsWord_CorrectLenght_Alphabet_UpperCase() {
        assertFalse(InputChecker.isValidAsWord("aBcde"));
    }
}
