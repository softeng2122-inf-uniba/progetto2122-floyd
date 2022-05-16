package it.uniba.app;


/**
 * < < Control > > Gestisce la partita e tutti i suoi componenti
 */

public class Match {
        
    public static final int NUM_OF_GUESSES = 6;
    public static final int NUM_OF_CELLS = 5;

    private String secretWord;
    private boolean isInProgress;

    UserInterface ui;

    private boolean isWordsmith; // utilizzato per capire quali comandi accettare durante il match
    
    /**
     * Istanzia un match, senza assegnazione diretta della parola segreta
     * 
     * @param isWordsmith
     */
    public Match(boolean isWordsmith, UserInterface ui) {
        isInProgress = false;
        secretWord = null;
        this.isWordsmith = isWordsmith;
        this.ui = ui;
    }

    /**
     * Istanzia un match, assegnando direttamente la parola segreta
     * 
     * @param isWordsmith
     * @param secretWord
     */
    public Match(boolean isWordsmith, String secretWord, UserInterface ui) {
        isInProgress = false;
        this.secretWord = secretWord;
        this.isWordsmith = isWordsmith;
        this.ui = ui;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }

    public boolean getIsInProgress() {
        return isInProgress;
    }

    public void setIsInProgress(boolean value) {
        isInProgress = value;
    }

    /**
     * Inizia la partita
     */
    public void start() {
        isInProgress = true;

        ui.drawMatrix();
        update();
    }

    private void update() {
        while (isInProgress) {
            String userInput = UserInput.get();
            ui.inGameCommands(userInput, isWordsmith);
            
            ui.drawMatrix();
        }


    }
}
