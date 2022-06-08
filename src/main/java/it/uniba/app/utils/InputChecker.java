package it.uniba.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.uniba.app.match.Match;

/**
 * {@literal <<noECB>>}
 * Contains methods to check the input strings based
 * on the game rules.
 */
public class InputChecker {
    private InputChecker() {
    }
    
    /**
     * Checks if the input is a command.
     * Only accepts strings that contain a "/" as first character.
     *
     * @param input The string to check.
     * @return {@code true} if the word is a command, {@code false}
     *         otherwise.
     */
    public static boolean isCommand(String input) {
        Matcher matcher = Pattern.compile("/.+").matcher(input);
        return matcher.matches();
    }

    /**
     * Checks if the input is a valid Word.
     * Only accepts strings that contain alphabet characters
     * and that are long a specific number, given by
     * {@link it.uniba.app.match.Match#NUM_OF_CELLS}.
     *
     * @param input The string to check.
     * @return {@code true} if the word is valid, {@code false}
     *         otherwise.
     */
    public static boolean isValidAsWord(String input) {
        if (input.length() == Match.NUM_OF_CELLS) {
            Matcher matcher = Pattern.compile("[a-z]*").matcher(input);
            return matcher.matches();
        } else {
            return false;
        }
    }
}
