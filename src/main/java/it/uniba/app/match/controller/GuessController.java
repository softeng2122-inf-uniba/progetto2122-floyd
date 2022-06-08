package it.uniba.app.match.controller;

import it.uniba.app.match.Guess;
import it.uniba.app.match.Word;

/**
 * {@literal <<control>>}
 * Manages a Guess object and its internal state.
 * <p>
 * Provides methods to check guess attempts.
 */
public class GuessController {

     /** Reference to the guess object that this controller is responsible of. */
    private final Guess guess;

    /** Reference to the secret word object this guess refers to. */
    private final Word secretWordToGuess;

    /** Instantiates a guess controller. */
    public GuessController() {
        this.guess = new Guess();
        this.secretWordToGuess = new Word();
    }

     /**
     * Copy constructor. Instantiates a copied guess controller.
     *
     * @param gc the guess controller to copy.
     */
    public GuessController(final GuessController gc) {
        this.guess = new Guess(gc.guess);
        this.secretWordToGuess = new Word(gc.secretWordToGuess);
    }

     /**
     * Starts the examination process on the given input.
     *
     * @param userInput the string to check.
     */
    public void startExamination(final String userInput) {
        new GuessExaminer(guess, secretWordToGuess).execute(userInput);
    }

    /**
     * @param str the secret word to guess.
     */
    public void setSecretWordToGuess(final String str) {
        secretWordToGuess.setString(str);
    }

    /**
     * @return {@code true} if the guess is correct, {@code false} otherwise.
     */
    public boolean isCorrect() {
        return guess.isCorrect();
    }

    /**
     * @param idx index of the requested cell.
     * @return a string representing ANSI sequence of the cell color.
     */
    public String getCellColor(final int idx) {
        return guess.getCellColor(idx);
    }

    /**
     * @param idx index of the cell
     * @return a char representing the cell's character.
     */
    public char getCellCharacter(final int idx) {
        return guess.getCellCharacter(idx);
    }

    /**
     * Resets the guess and its cells
     * to default state.
     */
    public void reset() {
        guess.reset();
    }
}
