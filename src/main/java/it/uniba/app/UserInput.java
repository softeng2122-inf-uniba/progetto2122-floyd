package it.uniba.app;

import java.util.Scanner;

/**
 * < < noECB > > Gestisce gli input da tastiera
 */
public class UserInput {
    private static Scanner in = new Scanner(System.in);

    public static String get() {
        return in.nextLine().toLowerCase();
    }
}
