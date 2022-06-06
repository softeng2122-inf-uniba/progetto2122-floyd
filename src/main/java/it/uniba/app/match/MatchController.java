package it.uniba.app.match;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ConsoleUtils;
import it.uniba.app.utils.InputChecker;
import it.uniba.app.utils.UserInput;

public class MatchController {
    private final UserInterface ui;

    private final Match match;

    public MatchController(UserInterface uiObj, Match matchObj) {
        this.match = matchObj;
        this.ui = uiObj;
    }

    /**
     * Inizia la partita
     */
    public void start() {
        match.setIsInProgress(true);

        UserInterface.printer.getGrid(match.getGuesses());

        update();
    }

    /**
     * Si occupa dell'aggiornamento della partita ad ogni input
     */
    private void update() {
        while (match.getCurrentGuessCtr() < 6 && match.getIsInProgress()) {
            String userInput = UserInput.get();

            if (!InputChecker.isCommand(userInput)) {
                tryGuess(userInput);
            } else {
                ui.getCommands(userInput);
            }
            UserInterface.printer.getGrid(match.getGuesses());
        }
        if (match.getCurrentGuessCtr() == 6 && match.getIsInProgress()) {
            UserInterface.printer.getMaxTriesReached(match.getSecretWord().getString());
        } else if (match.getGuess(match.getCurrentGuessCtr()).getIsCorrect()) {
            UserInterface.printer.getCorrectGuessNotification(match.getCurrentGuessCtr());
        } else {
            UserInterface.printer.getLeftCorrectlyNotification();
        }

    }

    /**
     * Si occupa di verificare il tentativo corrente
     * 
     * @param chosenWord parola scelta per il tentativo corrente
     */
    private void tryGuess(String chosenWord) {
        if (InputChecker.isValidAsWord(chosenWord)) {
            match.getGuess(match.getCurrentGuessCtr()).setChosenWord(chosenWord);
            if (match.getGuess(match.getCurrentGuessCtr()).checkGuess(match.getSecretWord())) {
                match.setIsInProgress(false);
            } else {
                match.incrementCurrentGuessCtr();
                match.getSecretWord().resetMarked();
                ConsoleUtils.clearScreen();
            }
        } else if (chosenWord.length() < 5) {
            UserInterface.printer.getIncompleteGuess();
        } else if (chosenWord.length() > 5) {
            UserInterface.printer.getExcessiveGuess();
        } else {
            UserInterface.printer.getInvalidGuess();
        }
    }
}