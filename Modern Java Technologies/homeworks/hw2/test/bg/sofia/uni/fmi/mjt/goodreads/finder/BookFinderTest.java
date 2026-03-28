package bg.sofia.uni.fmi.mjt.goodreads.finder;

import bg.sofia.uni.fmi.mjt.goodreads.book.Book;
import bg.sofia.uni.fmi.mjt.goodreads.tokenizer.TextTokenizer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookFinderTest {

    private static BookFinderAPI bookFinder;
    private static TextTokenizer textTokenizer;

    @BeforeAll
    public static void setUp() {
        String stopwords = """
            a
            about
            above
            """;

        String booksText = """
            1,"Harry Potter",J.K. Rowling,"Harry Potter","['Fantasy', 'Fiction', 'Young Adult', 'Classics']",4.47,"9,278,135",https://www.HarryPoter
            2,Pride and Prejudice,Jane Austen,"Pride and Prejudice","['Romance', 'Historical Fiction', 'Historical']",4.28,"3,944,155",https://www.Pride_and_Prejudice
            3,Sense and Sensibility,Jane Austen,"Penguin Classic edition.","['Classics', 'Romance', 'Historical Fiction', 'Historical']",4.08,"1,130,053",https://www.Sense_and_Sensibility
            """;

        Set<Book> books = Set.of(
            new Book("1", "Harry Potter", "J.K. Rowling", "Harry Potter description",
                List.of("Fantasy", "Fiction", "Young Adult", "Classics"), 4.47, 9278135, "https://www.HarryPoter"),
            new Book("2", "Pride and Prejudice", "Jane Austen", "Pride and Prejudice",
                List.of("Romance", "Historical Fiction", "Historical"), 4.28, 3944155, "https://www.Pride_and_Prejudice"),
            new Book("3", "Sense and Sensibility", "Jane Austen", "Penguin Classic edition.",
                List.of("Classics", "Romance", "Historical Fiction", "Historical"), 4.08, 1130053, "https://www.Sense_and_Sensibility")
        );

        textTokenizer = mock(TextTokenizer.class);
        bookFinder = new BookFinder(books, textTokenizer);
    }

    @Test
    public void testSearchByAuthorWithNull() {
        assertThrows(IllegalArgumentException.class, () -> bookFinder.searchByAuthor(null), "Searching by null author name should throw an exception");
    }

    @Test
    public void testSearchByAuthorWithEmpty() {
        assertThrows(IllegalArgumentException.class, () -> bookFinder.searchByAuthor(""), "Searching by empty author name should throw an exception");
    }

    @Test
    public void testSearchByAuthorWithCorrectInput() {
        List<Book> expectedResult = List.of(
            new Book("2", "Pride and Prejudice", "Jane Austen", "Pride and Prejudice",
                List.of("Romance", "Historical Fiction", "Historical"), 4.28, 3944155, "https://www.Pride_and_Prejudice"),
            new Book("3", "Sense and Sensibility", "Jane Austen", "Penguin Classic edition.",
                List.of("Classics", "Romance", "Historical Fiction", "Historical"), 4.08, 1130053, "https://www.Sense_and_Sensibility")
        );

        List<Book> currentResult = bookFinder.searchByAuthor("Jane Austen");
        assertTrue(expectedResult.containsAll(currentResult) && currentResult.containsAll(expectedResult),
            "Searching by correct author name should return a list of currentResult written by the author");
    }

    @Test
    public void testAllGenres() {
        Set<String> expectedResult = Set.of("Young Adult", "Classics", "Historical Fiction", "Fantasy", "Romance", "Historical", "Fiction");
        Set<String> currentResult = bookFinder.allGenres();
        assertTrue(expectedResult.containsAll(currentResult) && currentResult.containsAll(expectedResult),
            "Getting all genres should return a set of all genres from the books");
    }

    @Test
    public void testSearchByGenresWithNull() {
        assertThrows(IllegalArgumentException.class, () -> bookFinder.searchByGenres(null, MatchOption.MATCH_ANY), "Searching by null genres should throw an exception");
    }

    @Test
    public void testSearchByGenresWithMatchAny() {
        Set<String> genres = Set.of("Romance", "Historical");
        List<Book> expectedResult = List.of(
            new Book("2", "Pride and Prejudice", "Jane Austen", "Pride and Prejudice",
                List.of("Romance", "Historical Fiction", "Historical"), 4.28, 3944155, "https://www.Pride_and_Prejudice"),
            new Book("3", "Sense and Sensibility", "Jane Austen", "Penguin Classic edition.",
                List.of("Classics", "Romance", "Historical Fiction", "Historical"), 4.08, 1130053, "https://www.Sense_and_Sensibility")
        );

        List<Book> currentResult = bookFinder.searchByGenres(genres, MatchOption.MATCH_ANY);
        assertTrue(expectedResult.containsAll(currentResult) && currentResult.containsAll(expectedResult),
            "Searching by correct genres should return a list of currentResult that match the genres");
    }

    @Test
    public void testSearchByGenresWithMatchAll() {
        Set<String> genres = Set.of("Romance", "Historical", "Historical Fiction");
        List<Book> expectedResult = List.of(
            new Book("2", "Pride and Prejudice", "Jane Austen", "Pride and Prejudice",
                List.of("Romance", "Historical Fiction", "Historical"), 4.28, 3944155, "https://www.Pride_and_Prejudice"),
            new Book("3", "Sense and Sensibility", "Jane Austen", "Penguin Classic edition.",
                List.of("Classics", "Romance", "Historical Fiction", "Historical"), 4.08, 1130053, "https://www.Sense_and_Sensibility")
        );

        List<Book> currentResult = bookFinder.searchByGenres(genres, MatchOption.MATCH_ALL);
        assertTrue(expectedResult.containsAll(currentResult) && currentResult.containsAll(expectedResult),
            "Searching by correct genres should return a list of currentResult that match the genres");
    }

    @Test
    public void testSearchByKeywordsWithNull() {
        assertThrows(IllegalArgumentException.class, () -> bookFinder.searchByKeywords(null, MatchOption.MATCH_ANY), "Searching by null keywords should throw an exception");
    }

    @Test
    public void testSearchByKeywordsMatchAny() {
        Set<String> keywords = Set.of("potter");
        List<Book> expectedResult = List.of(
            new Book("1", "Harry Potter", "J.K. Rowling", "Harry Potter description",
                List.of("Fantasy", "Fiction", "Young Adult", "Classics"), 4.47, 9278135, "https://www.HarryPoter")
        );

        when(textTokenizer.tokenize("Harry Potter")).thenReturn(List.of("harry", "potter"));
        when(textTokenizer.tokenize("Harry Potter description")).thenReturn(List.of("harry", "potter", "description"));

        List<Book> currentResult = bookFinder.searchByKeywords(keywords, MatchOption.MATCH_ANY);
        assertTrue(expectedResult.containsAll(currentResult) && currentResult.containsAll(expectedResult),
            "Searching by correct keywords should return a list of currentResult that match the keywords");
    }

    @Test
    public void testSearchByKeywordsMatchAll() {
        Set<String> keywords = Set.of("harry", "potter");
        List<Book> expectedResult = List.of(
            new Book("1", "Harry Potter", "J.K. Rowling", "Harry Potter description",
                List.of("Fantasy", "Fiction", "Young Adult", "Classics"), 4.47, 9278135, "https://www.HarryPoter")
        );

        when(textTokenizer.tokenize("Harry Potter")).thenReturn(List.of("harry", "potter"));
        when(textTokenizer.tokenize("Harry Potter description")).thenReturn(List.of("harry", "potter", "description"));

        List<Book> currentResult = bookFinder.searchByKeywords(keywords, MatchOption.MATCH_ALL);
        assertTrue(expectedResult.containsAll(currentResult) && currentResult.containsAll(expectedResult),
            "Searching by correct keywords should return a list of currentResult that match the keywords");
    }

    @Test
    public void testSearchByKeywordsMatchAllSeparate() {
        Set<String> keywords = Set.of("potter", "description");
        List<Book> expectedResult = List.of(
            new Book("1", "Harry Potter", "J.K. Rowling", "Harry Potter description",
                List.of("Fantasy", "Fiction", "Young Adult", "Classics"), 4.47, 9278135, "https://www.HarryPoter")
        );

        when(textTokenizer.tokenize("Harry Potter")).thenReturn(List.of("harry", "potter"));
        when(textTokenizer.tokenize("Harry Potter description")).thenReturn(List.of("harry", "potter", "description"));

        List<Book> currentResult = bookFinder.searchByKeywords(keywords, MatchOption.MATCH_ALL);
        assertTrue(expectedResult.containsAll(currentResult) && currentResult.containsAll(expectedResult),
            "Searching by correct keywords should return a list of currentResult that match the keywords");
    }
    
}
