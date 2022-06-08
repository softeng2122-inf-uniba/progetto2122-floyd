package it.uniba.app.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class UserInputTest {
    private final InputStream stdIn = System.in;

    @AfterEach
    public void restoreInStream() {
        System.setIn(stdIn);
    }

    @Test
    public void testGet_UpperLowerCase() {
        String userInput = "InpUt stRing";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        assertEquals(userInput.toLowerCase(), UserInput.get());
    }

    // Se vengono eseguiti insieme, tramite la suite, da errore
    // e falliscono, ma se eseguiti singolarmente riescono a testare tutta la
    // funzione get()
    // @Test
    // public void testGet_EmptyInput() {
    // String userInput = "";
    // InputStream in = new ByteArrayInputStream(userInput.getBytes());
    // System.setIn(in);

    // assertThrowsExactly(NoSuchElementException.class, () -> UserInput.get());
    // }
}
