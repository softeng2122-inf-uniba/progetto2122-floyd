package it.uniba.app.utils;

import java.util.Scanner;

/**
 * < < noECB > > Gestisce gli input da tastiera
 */
public class UserInput {
    private static Scanner in = new Scanner(System.in, "UTF-8");

    public static String get() {
        String input = in.nextLine().toLowerCase();
        while (input.isEmpty()) {
            input = in.nextLine().toLowerCase();
        }
        return input;
    }
}
