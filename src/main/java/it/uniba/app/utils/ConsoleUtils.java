package it.uniba.app.utils;

/**
 * {@literal <<noECB>>}
 * Contains ANSI escape sequences for colors and clearing console.
 */
public final class ConsoleUtils {

    private ConsoleUtils() {
    }

    /** Resets text color and background color. */
    public static final String ANSI_RESET = "\u001B[0m";

    /** Sets text color to black. */
    public static final String ANSI_BLACK = "\u001B[30m";

    /** Sets background color to green. */
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    /** Sets background color to yellow. */
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

    /** Sets background color to white. */
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    /** Clears console. */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
