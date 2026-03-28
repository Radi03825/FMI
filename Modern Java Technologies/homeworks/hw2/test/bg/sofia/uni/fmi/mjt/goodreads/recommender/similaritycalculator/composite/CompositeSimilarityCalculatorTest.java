package bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.composite;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;
import bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.SimilarityCalculator;
import bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.descriptions.TFIDFSimilarityCalculator;
import bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.genres.GenresOverlapSimilarityCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompositeSimilarityCalculatorTest {

    private static SimilarityCalculator calculator;
    private static GenresOverlapSimilarityCalculator genresOverlapSimilarityCalculator;
    private static TFIDFSimilarityCalculator tfidfSimilarityCalculator;

    private static Book firstBook;
    private static Book secondBook;

    @BeforeAll
    public static void setUp() {
        firstBook =  new Book("1", "Harry Potter", "J.K. Rowling", "Harry Potter",
            List.of("Fantasy", "Fiction", "Young Adult", "Classics"), 4.47, 9278135, "https://www.HarryPoter");

        secondBook = new Book("2", "Pride and Prejudice", "Jane Austen", "Pride and Prejudice",
                List.of("Romance", "Historical Fiction", "Historical"), 4.28, 3944155, "https://www.Pride_and_Prejudice");

        genresOverlapSimilarityCalculator = mock(GenresOverlapSimilarityCalculator.class);
        tfidfSimilarityCalculator = mock(TFIDFSimilarityCalculator.class);

        Map<SimilarityCalculator, Double> similarityCalculators = Map.of(
            genresOverlapSimilarityCalculator, 0.6,
            tfidfSimilarityCalculator, 0.5
        );

        calculator = new CompositeSimilarityCalculator(similarityCalculators);
    }

    @Test
    public void testCalculateSimilarityWithNullBooks() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateSimilarity(null, null));
    }

    @Test
    public void testCalculateSimilarityWithCorrectData() {
        when(genresOverlapSimilarityCalculator.calculateSimilarity(firstBook, secondBook)).thenReturn(0.6);
        when(tfidfSimilarityCalculator.calculateSimilarity(firstBook, secondBook)).thenReturn(0.5);

        assertEquals(0.61, calculator.calculateSimilarity(firstBook, secondBook), 0.01);
    }
    
}
