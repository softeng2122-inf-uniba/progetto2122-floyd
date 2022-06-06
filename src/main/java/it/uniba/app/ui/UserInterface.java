package it.uniba.app.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.uniba.app.match.Match;
import it.uniba.app.user.Player;

/**
 * < < Boundary > > Gestisce l'interfaccia utente
 */
public class UserInterface {

    public static final UIPrinter printer = new UIPrinter();
    private static final UICommands commands = new UICommands();

    private final Match match;
    private final Player user;
    private String lastSecretWord = null;

    public UserInterface(final Player userObj) {
        this.user = userObj;
        this.match = new Match(this);
    }

    /**
     * Gestisce i comandi dati in input fuori dalla partita
     */
    public void getCommands(String userInput) {
        if (user.isWordsmith()) {
            switch (userInput) {
                case "/help": {
                    commands.help(user);
                    break;
                }
                case "/gioca": {
                    commands.play(lastSecretWord, match);
                    break;
                }
                case "/abbandona": {
                    commands.leave(match);
                    break;
                }
                case "/esci": {
                    commands.exit();
                    break;
                }
                case "/mostra": {
                    commands.showSecretWord(lastSecretWord);
                    break;
                }

                default: {
                    Matcher matcher = Pattern.compile("(/nuova) (.+)").matcher(userInput);
                    if (matcher.matches()) {
                        matcher.reset();
                        while (matcher.find()) {
                            int gc = matcher.groupCount();
                            commands.newSecretWord(this, matcher.group(gc), match);
                        }
                    } else {
                        System.out.println("Comando non riconosciuto. /help per visualizzare la lista dei comandi.");
                    }
                    break;
                }
            }
        } else {
            switch (userInput) {
                case "/help": {
                    commands.help(user);
                    break;
                }
                case "/gioca": {
                    commands.play(lastSecretWord, match);
                    break;
                }
                case "/abbandona": {
                    commands.leave(match);
                    break;
                }
                case "/esci": {
                    commands.exit();
                    break;
                }
                default: {
                    System.out.println("Comando non riconosciuto. /help per visualizzare la lista dei comandi.");
                    break;
                }
            }
        }
    }

    public void setLastSecretWord(String str) {
        this.lastSecretWord = str;
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
