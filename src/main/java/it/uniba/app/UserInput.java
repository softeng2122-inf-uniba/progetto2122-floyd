package it.uniba.app;

import java.util.Scanner;

public class UserInput {
    private static Scanner in = new Scanner(System.in);

    public static String get() {
        return in.nextLine().toLowerCase();
    }
}
