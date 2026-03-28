package bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.genres;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;
import bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.SimilarityCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GenresOverlapSimilarityCalculatorTest {

    private static SimilarityCalculator calculator;
    private static List<Book> books;

    @BeforeAll
    public static void setUp() {
        calculator = new GenresOverlapSimilarityCalculator();
        books = List.of(
                new Book("1", "Harry Potter", "J.K. Rowling", "Harry Potter",
                    List.of("Fantasy", "Fiction", "Young Adult", "Classics"), 4.47, 9278135, "https://www.HarryPoter"),
                new Book("2", "Pride and Prejudice", "Jane Austen", "Pride and Prejudice",
                    List.of("Romance", "Historical Fiction", "Historical"), 4.28, 3944155, "https://www.Pride_and_Prejudice"),
                new Book("3", "Sense and Sensibility", "Jane Austen", "Penguin Classic edition.",
                    List.of("Classics", "Romance", "Historical Fiction", "Historical"), 4.08, 1130053, "https://www.Sense_and_Sensibility")
        );
    }

    @Test
    public void testCalculateSimilarityWithNullBooks() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateSimilarity(null, null));
    }

    @Test
    public void testCalculateSimilarityWithSameBooks() {
        Book firstBook = books.getFirst();

        assertEquals(1, calculator.calculateSimilarity(firstBook, firstBook), 0.01);
    }

    @Test
    public void testCalculateSimilarityWithDifferentGenres() {
        Book firstBook = books.get(0);
        Book secondBook = books.get(1);
        assertEquals(0, calculator.calculateSimilarity(firstBook, secondBook), 0.01);
    }

    @Test
    public void testCalculateSimilarityWithSomeCommonGenres() {
        Book firstBook = books.get(0);
        Book secondBook = books.get(2);
        assertEquals(0.25, calculator.calculateSimilarity(firstBook, secondBook), 0.01);
    }

}
