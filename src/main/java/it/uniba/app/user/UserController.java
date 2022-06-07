package it.uniba.app.user;

public class UserController {
    private final Player user;

    public UserController(final Player player) {
        this.user = player;
    }

    public boolean isWordsmith() {
        return user.isWordsmith();
    }

    public String[] getHelpCommands() {
        return user.getHelpCommands();
    }
}
