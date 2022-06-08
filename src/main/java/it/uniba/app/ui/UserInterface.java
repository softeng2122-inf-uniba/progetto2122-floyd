package it.uniba.app.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.user.UserController;
import it.uniba.app.ui.control.ExitRequestProcessor;
import it.uniba.app.ui.control.HelpRequestProcessor;
import it.uniba.app.ui.control.LeaveRequestProcessor;
import it.uniba.app.ui.control.NewSecretWordProcessor;
import it.uniba.app.ui.control.PlayRequestProcessor;
import it.uniba.app.ui.control.ShowSecretWordProcessor;

/**
 * {@literal <<boundary>>}
 * Manages the I/O operations of the game.
 */
public class UserInterface {

    /** Responsible of printing messages on console. */
    public static final UIPrinter printer = new UIPrinter();

    /** Reference to match controller. */
    private final MatchController matchController;

    /** Reference to user controller. */
    private final UserController userController;

    /**
     * Holds the latest secret word to start
     * new matches with the previous word.
     */
    private String lastSecretWord;

    /**
     * @param userControllerObj the user controller linked to this ui.
     */
    public UserInterface(final UserController userControllerObj) {
        this.userController = userControllerObj;
        this.matchController = new MatchController(this);
    }

    /**
     * Dispatches input to the relative command processor.
     *
     * @param userInput command taken in input.
     */
    public void getCommands(final String userInput) {
        if (userController.isWordsmith()) {
            switch (userInput) {
                case "/help":
                    new HelpRequestProcessor(userController).execute();
                    break;
                case "/gioca":
                    new PlayRequestProcessor(matchController).execute(lastSecretWord);
                    break;
                case "/abbandona":
                    new LeaveRequestProcessor(matchController).execute();
                    break;
                case "/esci":
                    new ExitRequestProcessor().execute();
                    break;
                case "/mostra":
                    new ShowSecretWordProcessor().execute(lastSecretWord);
                    break;
                default:
                    Matcher matcher = Pattern.compile("(/nuova) (.+)").matcher(userInput);
                    if (matcher.matches()) {
                        matcher.reset();
                        while (matcher.find()) {
                            int gc = matcher.groupCount();
                            new NewSecretWordProcessor(this, matchController).execute(matcher.group(gc));
                        }
                    } else {
                        printer.getInvalidCommand();
                    }
                    break;
            }
        } else {
            switch (userInput) {
                case "/help":
                    new HelpRequestProcessor(userController).execute();
                    break;
                case "/gioca":
                    new PlayRequestProcessor(matchController).execute(lastSecretWord);
                    break;
                case "/abbandona":
                    new LeaveRequestProcessor(matchController).execute();
                    break;
                case "/esci":
                    new ExitRequestProcessor().execute();
                    break;
                default:
                    printer.getInvalidCommand();
                    break;
            }
        }
    }

    /**
     * Sets the latest secret word.
     *
     * @param str the new secret word.
     */
    public void setLastSecretWord(final String str) {
        this.lastSecretWord = str;
    }

    /**
     * @return the latest secret word.
     */
    public String getLastSecretWord() {
        return this.lastSecretWord;
    }

    // Takes input for the secret word.
    //
    // @deprecated not used because you are not forced
    // to input a secret word when starting a match anymore.
    // @return the input string if it's valid.
    //
    // public String inputSecretWord() {
    // while (true) {
    // String inputString = UserInput.get();

    // if (UserInput.isValidAsWord(inputString)) {
    // return inputString;
    // }
    // }
    // }

}
