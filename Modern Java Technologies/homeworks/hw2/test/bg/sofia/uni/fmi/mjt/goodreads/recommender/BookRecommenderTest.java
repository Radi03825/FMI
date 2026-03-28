package bg.sofia.uni.fmi.mjt.goodreads.recommender;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;
import bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.SimilarityCalculator;
import bg.sofia.uni.fmi.mjt.goodreads.recommender.similaritycalculator.genres.GenresOverlapSimilarityCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class BookRecommenderTest {

    private static BookRecommender recommender;
    private static List<Book> books;

    @BeforeAll
    public static void setUp() {
        books = List.of(
                new Book("1", "Harry Potter", "J.K. Rowling", "Harry Potter",
                    List.of("Fantasy", "Fiction", "Young Adult", "Classics"), 4.47, 9278135, "https://www.HarryPoter"),
                new Book("2", "Pride and Prejudice", "Jane Austen", "Pride and Prejudice",
                    List.of("Romance", "Historical Fiction", "Historical"), 4.28, 3944155, "https://www.Pride_and_Prejudice"),
                new Book("3", "Sense and Sensibility", "Jane Austen", "Penguin Classic edition.",
                    List.of("Classics", "Romance", "Historical Fiction", "Historical"), 4.08, 1130053, "https://www.Sense_and_Sensibility")
        );

        SimilarityCalculator similarityCalculator = mock(GenresOverlapSimilarityCalculator.class);

        recommender = new BookRecommender(Set.of(books.get(0), books.get(1)), similarityCalculator);
    }

    @Test
    public void testRecommendBooksWithNullBook() {
        assertThrows(IllegalArgumentException.class, () -> recommender.recommendBooks(null, 1), "Recommend book with null book should throw an exception");
    }

    @Test
    public void testRecommendBooksWithInvalidMaxN() {
        assertThrows(IllegalArgumentException.class, () -> recommender.recommendBooks(books.get(0), -1), "Recommend book with zero count should throw an exception");
    }

    @Test
    public void testRecommendBooksWithCorrectData() {
        Book book = books.getFirst();

        Map<Book, Double> expectedResult = Map.of(
            book, 0.0
        );

        SortedMap<Book, Double> result = recommender.recommendBooks(book, 1);

        assertEquals(expectedResult, result, "Recommend book with correct data should return the correct result");
    }
    
}
