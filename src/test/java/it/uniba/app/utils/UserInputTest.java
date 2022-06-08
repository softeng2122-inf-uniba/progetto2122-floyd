package it.uniba.app.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserInputTest {
    private InputStream stdIn;

    @BeforeEach
    public void setUp() {
        stdIn = System.in;
    }

    @Test
    public void testGet_UpperLowerCase() {
        String userInput = "InpUt stRing";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        assertEquals(userInput.toLowerCase(), UserInput.get());
    }

    @Test
    public void testGet_EmptyInput() {
        String userInput = "";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        assertThrowsExactly(NoSuchElementException.class, () -> UserInput.get());
    }

    @AfterEach
    public void restoreStream() {
        System.setIn(stdIn);
    }
}
