package it.uniba.app.utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.uniba.app.match.Match;

/**
 * < < noECB > > Gestisce gli input da tastiera
 */
public class UserInput {
    private static Scanner in = new Scanner(System.in, "UTF-8");

    public static String get() {
        return in.nextLine().toLowerCase();
    }

    public static boolean isCommand(String input) {
        Matcher matcher = Pattern.compile("/.+").matcher(input);
        return matcher.matches();
    }

    public static boolean isValidAsWord(String input) {
        if (input.length() == Match.NUM_OF_CELLS) {
            Matcher matcher = Pattern.compile("[A-Za-z]*").matcher(input);
            return matcher.matches();
        } else
            return false;
    }
}
