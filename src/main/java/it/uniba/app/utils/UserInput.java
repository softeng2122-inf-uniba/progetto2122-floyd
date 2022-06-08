package it.uniba.app.utils;

import java.util.Scanner;

/**
 * {@literal <<boundary>>}
 * Manages the input operations.
 */
public final class UserInput {

    private UserInput() {
    }

    /** Scanner where we store the input stream. */

    /**
     * Gets a line of input from the user.
     *
     * @return the String taken from user input,
     *         automatically converted to lower case.
     */
    public static String get() {
        Scanner in = new Scanner(System.in, "UTF-8");
        String input = in.nextLine().toLowerCase();
        while (input.isEmpty()) {
            input = in.nextLine().toLowerCase();
        }
        return input;
    }
}
