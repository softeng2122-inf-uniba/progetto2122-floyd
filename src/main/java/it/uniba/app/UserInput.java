package it.uniba.app;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * < < noECB > > Gestisce gli input da tastiera
 */
public class UserInput {
    private static Scanner in = new Scanner(System.in);

    public static String get() {
        return in.nextLine().toLowerCase();
    }

    public static boolean isValidAsWord(String input) {
        if (input.length() == 5) {
            Matcher matcher = Pattern.compile("[A-Za-z]*").matcher(input);
            return matcher.matches();
        } else
            return false;
    }
}
