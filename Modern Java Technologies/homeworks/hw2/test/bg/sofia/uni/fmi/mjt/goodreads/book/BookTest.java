package bg.sofia.uni.fmi.mjt.goodreads.book;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookTest {

    @Test
    public void testBookOfWithNull() {
        assertThrows(IllegalArgumentException.class, () -> Book.of(null), "Book of with null should throw IllegalArgumentException");
    }

    @Test
    public void testBookOfWithEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> Book.of(new String[]{}), "Book of with empty string should throw IllegalArgumentException");
    }

    @Test
    public void testBookOfWithWrongCountOfArguments() {
        assertThrows(IllegalArgumentException.class, () -> Book.of(new String[]{"1", "2", "3", "4", "5", "6"}), "Book of with wrong count of arguments should throw IllegalArgumentException");
    }

    @Test
    public void testBookOfWithValidInput() {
        Book book = Book.of(new String[] {
            "7",
            "The Great Gatsby",
            "F. Scott Fitzgerald",
            "The Great Gatsby, F. Scott Fitzgerald's third book, stands as the supreme achievement of his career.",
            "['Classics', 'Fiction', 'School', 'Historical Fiction', 'Literature', 'Romance', 'Novels']",
            "3.93",
            "4,839,642",
            "https://www.goodreads.com/book/show/4671.The_Great_Gatsby"
        });

        List<String> expectedGenres =
            List.of("Classics", "Fiction", "School", "Historical Fiction", "Literature", "Romance", "Novels");

        assertEquals("7", book.ID(), "Book id should be correct");
        assertEquals("The Great Gatsby", book.title(), "Book title should be correct");
        assertEquals("F. Scott Fitzgerald", book.author(), "Book author should be correct");
        assertEquals("The Great Gatsby, F. Scott Fitzgerald's third book, stands as the supreme achievement of his career.", book.description(), "Book description should be correct");
        assertIterableEquals(expectedGenres, book.genres(), "Book genre should be correct");
        assertEquals(3.93, book.rating(), 0.01, "Book rating should be correct");
        assertEquals(4839642, book.ratingCount(), "Book rating count should be correct");
        assertEquals("https://www.goodreads.com/book/show/4671.The_Great_Gatsby", book.URL(), "Book url should be correct");
    }
    
}
