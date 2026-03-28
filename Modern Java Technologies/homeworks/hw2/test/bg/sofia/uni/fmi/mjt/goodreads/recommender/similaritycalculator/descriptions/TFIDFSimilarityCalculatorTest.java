package bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.descriptions;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;
import bg.sofia.uni.fmi.mjt.goodreads.tokenizer.TextTokenizer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TFIDFSimilarityCalculatorTest {

    private static TFIDFSimilarityCalculator tfidfSimilarityCalculator;
    private static List<Book> books;
    private static TextTokenizer textTokenizer;

    @BeforeAll
    public static void setUp() {
        books = List.of(
                new Book("1", "Harry Potter", "J.K. Rowling", "This is the description of the book Harry Potter",
                    List.of("Fantasy", "Fiction", "Young Adult", "Classics"), 4.47, 9278135, "https://www.HarryPoter"),
                new Book("2", "Pride and Prejudice", "Jane Austen", "This is the description of the book Pride and Prejudice",
                    List.of("Romance", "Historical Fiction", "Historical"), 4.28, 3944155, "https://www.Pride_and_Prejudice"),
                new Book("3", "Sense and Sensibility", "Jane Austen", "This is the description of the book Penguin Classic edition.",
                    List.of("Classics", "Romance", "Historical Fiction", "Historical"), 4.08, 1130053, "https://www.Sense_and_Sensibility")
        );

        textTokenizer = mock(TextTokenizer.class);

        tfidfSimilarityCalculator = new TFIDFSimilarityCalculator(new HashSet<>(books), textTokenizer);
    }

    @Test
    public void testComputeTFWithNull() {
        assertThrows(IllegalArgumentException.class, () -> tfidfSimilarityCalculator.computeTF(null), "Computing TF with null book should throw an exception");
    }

    @Test
    public void testComputeTFWithCorrectInput() {
        Book book = books.getFirst();

        when(textTokenizer.tokenize(book.description())).thenReturn(List.of("description", "book", "harry", "potter"));

        Map<String, Double> expectedResult = Map.of(
            "harry", 0.25,
            "book", 0.25,
            "description", 0.25,
            "potter", 0.25
        );
        Map<String, Double> computedTF = tfidfSimilarityCalculator.computeTF(book);

        assertEquals(expectedResult, computedTF,
            "Computing TF with correct input should return a map with the correct values");
    }

    @Test
    public void testComputeIDFWithNull() {
        assertThrows(IllegalArgumentException.class, () -> tfidfSimilarityCalculator.computeIDF(null), "Computing IDF with null book should throw an exception");
    }

    @Test
    public void testComputeIDFWithCorrectInput() {
        Book book = books.getFirst();

        when(textTokenizer.tokenize(book.description())).thenReturn(List.of("description", "book", "harry", "potter"));

        Map<String, Double> expectedResult = Map.of(
            "book", 1.1,
            "harry", 1.1,
            "potter", 1.1,
            "description", 1.1
        );
        Map<String, Double> computedIDF = tfidfSimilarityCalculator.computeIDF(book);

        for (Map.Entry<String, Double> expectedEntry : expectedResult.entrySet()) {
            assertEquals(expectedEntry.getValue(), computedIDF.get(expectedEntry.getKey()), 0.01,
                "Computing IDF with correct input should return a map with the correct values");
        }
    }

    @Test
    public void testComputeTFIDFWithNull() {
        assertThrows(IllegalArgumentException.class, () -> tfidfSimilarityCalculator.computeTFIDF(null), "Computing TFIDF with null book should throw an exception");
    }

    @Test
    public void testComputeTFIDFWithCorrectInput() {
        Book book = books.getFirst();

        when(textTokenizer.tokenize(book.description())).thenReturn(List.of("description", "book", "harry", "potter"));

        Map<String, Double> tf = Map.of(
            "book", 0.25,
            "harry", 0.25,
            "potter", 0.25,
            "description", 0.25
        );
        Map<String, Double> idf = Map.of(
            "book", 1.1,
            "harry", 1.1,
            "potter", 1.1,
            "description", 1.1
        );

        Map<String, Double> expectedResult = Map.of(
            "book", 0.275,
            "harry", 0.275,
            "potter", 0.275,
            "description", 0.275
        );
        Map<String, Double> computedTFIDF = tfidfSimilarityCalculator.computeTFIDF(book);

        for (Map.Entry<String, Double> expectedEntry : expectedResult.entrySet()) {
            assertEquals(expectedEntry.getValue(), computedTFIDF.get(expectedEntry.getKey()), 0.01,
                "Computing TFIDF with correct input should return a map with the correct values");
        }
    }

    @Test
    public void testCalculateSimilarityWithNullBooks() {
        assertThrows(IllegalArgumentException.class, () -> tfidfSimilarityCalculator.calculateSimilarity(null, null));
    }

    @Test
    public void testCalculateSimilarityWithCorrectInput() {
        Book firstBook = books.getFirst();
        Book secondBook = books.get(1);

        when(textTokenizer.tokenize(firstBook.description())).thenReturn(List.of("description", "book", "harry", "potter"));
        when(textTokenizer.tokenize(secondBook.description())).thenReturn(List.of("description", "book", "pride", "prejudice"));

        assertEquals(0.11, tfidfSimilarityCalculator.calculateSimilarity(firstBook, secondBook), 0.01, "Calculating similarity with correct input should return the correct value");
    }
    
}
