package bg.sofia.uni.fmi.mjt.poll.server.output.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonParserTest {

    private static OutputParser outputParser;

    @BeforeAll
    public static void setUp() {
        outputParser = new JsonParser();
    }

    @Test
    public void testCreateResponseWithNullStatus() {
        assertThrows(IllegalArgumentException.class, () -> outputParser.createResponse(null, "message"));
    }

    @Test
    public void testCreateResponseWithNullMessage() {
        assertThrows(IllegalArgumentException.class, () -> outputParser.createResponse("status", null));
    }

    @Test
    public void testCreateResponseValidInput() {
        String expected = "{\"status\":\"OK\",\"message\":\"Everything is fine\"}";
        String actual = outputParser.createResponse("OK", "Everything is fine");

        assertEquals(expected, actual, "OutputParser should return correct response when input is valid");
    }
    
}
