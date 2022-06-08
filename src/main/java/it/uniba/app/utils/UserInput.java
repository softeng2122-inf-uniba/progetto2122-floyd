package it.uniba.app.utils;

import java.util.Scanner;

/**
 * {@literal <<noECB>>}
 * Manages the input operations.
 */
public class UserInput {

    /** Scanner where we store the input stream. */
    private static Scanner in = new Scanner(System.in, "UTF-8");

    /**
     * Gets a line of input from the user.
     *
     * @return the String taken from user input,
     *         automatically converted to lower case.
     */
    public static String get() {
        String input = in.nextLine().toLowerCase();
        while (input.isEmpty()) {
            input = in.nextLine().toLowerCase();
        }
        return input;
    }
}
