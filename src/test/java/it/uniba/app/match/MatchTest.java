package it.uniba.app.match;

import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.controller.GuessController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;

/** Test Match class. */
public final class MatchTest {
    /** UserInterface object. */
    private UserInterface ui;
    /** Class to test. */
    private Match match;

    /** Setups the test. */
    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        match = new Match(ui);
    }

    /** Test getGuessControllers to verify if it's an actual deep copy. */
    @Test
    public void testGetGuessControllers_IsDeepCopy() {
        GuessController[] guessControllers = match.getGuessControllers();
        assertNotSame(guessControllers, match.getGuessControllers());
    }

    /** Test setGuessControllers to verify if it saves a new deep copy. */
    @Test
    public void testSetGuessControllers_IsDeepCopy() {
        GuessController[] guessControllers = match.getGuessControllers();
        guessControllers[0].setSecretWordToGuess("prova");
        match.setGuessControllers(guessControllers);
        assertNotSame(guessControllers, match.getGuessControllers());
    }
}
