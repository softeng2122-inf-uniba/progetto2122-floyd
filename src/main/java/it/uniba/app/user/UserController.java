package it.uniba.app.user;

public class UserController {
    private final Player user;

    public UserController(final String playerType) {
        if (playerType.equals("Player")) {
            this.user = new Player();
        } else if (playerType.equals("Wordsmith")) {
            this.user = new Wordsmith();
        } else {
            throw new IllegalArgumentException("Undefined player type." +
                    "Must be Player or Wordsmith");
        }
    }

    public boolean isWordsmith() {
        return user.isWordsmith();
    }

    public String[] getHelpCommands() {
        return user.getHelpCommands();
    }
}
