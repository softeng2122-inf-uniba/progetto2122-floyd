package it.uniba.app.match.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.match.Match;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.user.UserController;

public class MatchStarterTest {
    private Match match;
    private UserInterface ui;
    private InputStream stdIn;

    @BeforeEach
    public void setUp() {
        ui = new UserInterface(new UserController("Wordsmith"));
        match = new Match(ui);
        stdIn = System.in;
    }

    @Test
    public void testExecute_Correct_Endgame() {
        match.setSecretWord("prova");

        String userInput = "prova";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        new MatchStarter(match, ui).execute();
        assertFalse(match.isInProgress());
        assertEquals(0, match.getCurrentGuessCtr());
    }

    @Test
    public void testExecute_Incorrect_EndGame() {
        match.setSecretWord("prova");
        match.incrementCurrentGuessCtr();
        match.incrementCurrentGuessCtr();
        match.incrementCurrentGuessCtr();
        match.incrementCurrentGuessCtr();
        match.incrementCurrentGuessCtr();

        String userInput = "bravo" + System.lineSeparator();
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        new MatchStarter(match, ui).execute();
        assertEquals(6, match.getCurrentGuessCtr());
    }

    @AfterEach
    public void restoreStream() {
        System.setIn(stdIn);
    }
}
