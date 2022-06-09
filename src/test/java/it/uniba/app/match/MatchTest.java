package it.uniba.app.match;

import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.controller.GuessController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;

public final class MatchTest {
    private UserInterface ui;
    private Match match;

    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        match = new Match(ui);
    }

    @Test
    public void testGetGuessControllers_IsDeepCopy() {
        GuessController[] guessControllers = match.getGuessControllers();
        assertNotSame(guessControllers, match.getGuessControllers());
    }

    @Test
    public void testSetGuessControllers_IsDeepCopy() {
        GuessController[] guessControllers = match.getGuessControllers();
        guessControllers[0].setSecretWordToGuess("prova");
        match.setGuessControllers(guessControllers);
        assertNotSame(guessControllers, match.getGuessControllers());
    }
}
