package it.uniba.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.uniba.app.match.Match;

public class InputChecker {
    private InputChecker() {
    }

    public static boolean isCommand(String input) {
        Matcher matcher = Pattern.compile("/.+").matcher(input);
        return matcher.matches();
    }

    public static boolean isValidAsWord(String input) {
        if (input.length() == Match.NUM_OF_CELLS) {
            Matcher matcher = Pattern.compile("[a-z]*").matcher(input);
            return matcher.matches();
        } else {
            return false;
        }
    }
}
