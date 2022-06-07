package it.uniba.app.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.user.Player;

/**
 * < < Boundary > > Gestisce l'interfaccia utente
 */
public class UserInterface {

    public static final UIPrinter printer = new UIPrinter();
    private static final UICommands commands = new UICommands();

    private final MatchController matchController;
    private final Player user;
    private String lastSecretWord = null;

    public UserInterface(final Player userObj) {
        this.user = userObj;
        this.matchController = new MatchController(this);
    }

    public void setLastSecretWord(String str) {
        this.lastSecretWord = str;
    }

    /**
     * Gestisce i comandi dati in input
     */
    public void getCommands(String userInput) {
        if (user.isWordsmith()) {
            switch (userInput) {
                case "/help":
                    commands.help(user);
                    break;
                case "/gioca":
                    commands.play(lastSecretWord, matchController);
                    break;
                case "/abbandona":
                    commands.leave(matchController);
                    break;
                case "/esci":
                    commands.exit();
                    break;
                case "/mostra":
                    commands.showSecretWord(lastSecretWord);
                    break;
                default:
                    Matcher matcher = Pattern.compile("(/nuova) (.+)").matcher(userInput);
                    if (matcher.matches()) {
                        matcher.reset();
                        while (matcher.find()) {
                            int gc = matcher.groupCount();
                            commands.newSecretWord(this, matcher.group(gc), matchController);
                        }
                    } else {
                        printer.getInvalidCommand();
                    }
                    break;
            }
        } else {
            switch (userInput) {
                case "/help":
                    commands.help(user);
                    break;
                case "/gioca":
                    commands.play(lastSecretWord, matchController);
                    break;
                case "/abbandona":
                    commands.leave(matchController);
                    break;
                case "/esci":
                    commands.exit();
                    break;
                default:
                    printer.getInvalidCommand();
                    break;
            }
        }
    }

    //
    // Prende lo user input per la parola segreta
    //
    // @return restituisce l'input dato se è valido
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
