package it.uniba.app.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
        InputStream in = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        assertEquals(userInput.toLowerCase(), UserInput.get());
    }

    @Test
    public void testGet_EmptyInput() {
        String userInput = "";
        InputStream in = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        assertThrowsExactly(NoSuchElementException.class, () -> UserInput.get());
    }

    @Test
    public void testGet_Empty_ThenCorrect() {
        String userInput = "" + System.lineSeparator()
                + "string";
        InputStream in = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        assertEquals("string", UserInput.get());
    }

    @AfterEach
    public void restoreStream() {
        System.setIn(stdIn);
        UserInput.refreshStream();
    }
}
