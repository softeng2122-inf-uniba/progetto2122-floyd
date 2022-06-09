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

/** Tests UserInput class. */
public final class UserInputTest {

    /** Standard InputStream. */
    private InputStream stdIn;

    /** Setups the test. */
    @BeforeEach
    public void setUp() {
        stdIn = System.in;
    }

    /** Tests get method with an upper and lowercase input. */
    @Test
    public void testGet_UpperLowerCase() {
        String userInput = "InpUt stRing";
        InputStream in = new ByteArrayInputStream(
                userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        assertEquals(userInput.toLowerCase(), UserInput.get());
    }

    /** Tests get method with an empty input. */
    @Test
    public void testGet_EmptyInput() {
        String userInput = "";
        InputStream in = new ByteArrayInputStream(
                userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        assertThrowsExactly(NoSuchElementException.class,
                () -> UserInput.get());
    }

    /** Tests get method with an empty and then correct input. */
    @Test
    public void testGet_Empty_ThenCorrect() {
        String userInput = "" + System.lineSeparator()
                + "string";
        InputStream in = new ByteArrayInputStream(
                userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        UserInput.refreshStream();

        assertEquals("string", UserInput.get());
    }

    /** Restores the std I/O streams. */
    @AfterEach
    public void restoreStream() {
        System.setIn(stdIn);
        UserInput.refreshStream();
    }
}
