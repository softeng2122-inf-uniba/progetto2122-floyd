package it.uniba.app.user;

/**
 * {@literal <<control>>}
 * Wraps a user entity and controls communications with it.
 */
public class UserController {

    /** The currently active user object reference. */
    private final Player user;

    /**
     * @param playerType the user type you want to instantiate.
     *                   Must be {@code "Player"} or {@code "Wordsmith"}.
     */
    public UserController(final String playerType) {
        if (playerType.equals("Player")) {
            this.user = new Player();
        } else if (playerType.equals("Wordsmith")) {
            this.user = new Wordsmith();
        } else {
            throw new IllegalArgumentException("Undefined player type."
                + "Must be Player or Wordsmith");
        }
    }

    /**
     * @return {@code true} if the user is a Wordsmith, {@code false} if not.
     */
    public boolean isWordsmith() {
        return user.isWordsmith();
    }

    /**
     * @return a String array of all possible commands the user can use.
     */
    public String[] getHelpCommands() {
        return user.getHelpCommands();
    }
}
