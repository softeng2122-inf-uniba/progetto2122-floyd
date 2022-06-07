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
 * < < Boundary > > Gestisce l'interfaccia utente
 */
public class UserInterface {

    public static final UIPrinter printer = new UIPrinter();

    private final MatchController matchController;
    private final UserController userController;
    private String lastSecretWord = null;

    public UserInterface(final UserController userControllerObj) {
        this.userController = userControllerObj;
        this.matchController = new MatchController(this);
    }

    /**
     * Gestisce i comandi dati in input
     */
    public void getCommands(String userInput) {
        if (userController.isWordsmith()) {
            switch (userInput) {
                case "/help":
                    new HelpRequestProcessor(userController).execute();
                    break;
                case "/gioca":
                    new PlayRequestProcessor(matchController);
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
                    new PlayRequestProcessor(matchController);
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
