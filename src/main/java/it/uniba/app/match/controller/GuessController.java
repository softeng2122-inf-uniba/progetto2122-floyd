package it.uniba.app.match.controller;

import it.uniba.app.match.Guess;
import it.uniba.app.match.Word;

/**
 * {@literal <<control>>}
 * Controls a Guess object. Provides methods to check guess attempts.
 */
public class GuessController {
    private final Guess guess;
    private final Word secretWordToGuess;

    public GuessController() {
        this.guess = new Guess();
        this.secretWordToGuess = new Word();
    }

    public GuessController(final GuessController gc) {
        this.guess = new Guess(gc.guess);
        this.secretWordToGuess = new Word(gc.secretWordToGuess);
    }

    public void startExamination(final String userInput) {
        new GuessExaminer(guess, secretWordToGuess).execute(userInput);
    }

    public void setSecretWordToGuess(final String str) {
        secretWordToGuess.setString(str);
    }

    public boolean isCorrect() {
        return guess.isCorrect();
    }

    public String getCellColor(final int idx) {
        return guess.getCellColor(idx);
    }

    public char getCellCharacter(final int idx) {
        return guess.getCellCharacter(idx);
    }

    public void reset() {
        guess.reset();
    }
}
