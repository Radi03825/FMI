package bg.sofia.uni.fmi.mjt.goodreads.tokenizer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextTokenizerTest {

    private static TextTokenizer textTokenizer;

    @BeforeAll
    public static void setUp() {
        String stopwords = """
            a
            about
            above
            """;

        textTokenizer = new TextTokenizer(new StringReader(stopwords));
    }

    @Test
    public void testTokenizeWithNull() {
        assertThrows(IllegalArgumentException.class, () -> textTokenizer.tokenize(null), "Tokenizing null input should throw an exception");
    }

    @Test
    public void testTokenizeWithEmpty() {
        assertIterableEquals(List.of(), textTokenizer.tokenize(""), "Tokenizing empty input should return an list with one empty string");
    }

    @Test
    public void testTokenizeWithCorrectInput() {
        String input = "The Call of the Wild is a tale about unbreakable spirit";
        List<String> expected = List.of("the", "call", "of", "the", "wild", "is", "tale", "unbreakable", "spirit");
        List<String> tokenize = textTokenizer.tokenize(input);
        assertIterableEquals(expected, tokenize, "Tokenizing correct input should return a list of words without stopwords");
    }
    
}
